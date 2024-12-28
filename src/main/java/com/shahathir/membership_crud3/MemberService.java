package com.shahathir.membership_crud3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ImageService imageService;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(int id) {
        return memberRepository.findById(id);
    }

    public Member prepareMemberForSave(Member member) {
        // Calculate age from date of birth
        int ageCalculated = MemberUtils.calculateAge(member.getDateOfBirth());
        member.setAge(ageCalculated);

        // Calculate expiration date from join date and membership duration
        LocalDate expirationDateCalculated = MemberUtils.calculateExpirationDate(member.getJoinDate(),
                member.getMembershipDuration());
        member.setExpirationDate(expirationDateCalculated);

        // Determine membership status based on expiration date
        String statusDecided = MemberUtils.getMembershipStatus(member.getExpirationDate());
        member.setStatus(statusDecided);

        return member;
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(int id) {
        memberRepository.deleteById(id);
    }

    public List<Member> searchMembers(
            String id, String firstN, String lastN, String type, String username,
            String email, String phone, String address, String dobStart, String dobEnd,
            String age, String joinDateStart, String joinDateEnd, String status,
            String duration, String expirationDateStart, String expirationDateEnd) {

        Integer idInt = id != null ? Integer.parseInt(id) : null;
        Integer ageInt = age != null ? Integer.parseInt(age) : null;
        Integer durationInt = duration != null ? Integer.parseInt(duration) : null;

        LocalDate dobStartDate = dobStart != null ? LocalDate.parse(dobStart) : null;
        LocalDate dobEndDate = dobEnd != null ? LocalDate.parse(dobEnd) : null;
        LocalDate joinStartDateLocal = joinDateStart != null ? LocalDate.parse(joinDateStart) : null;
        LocalDate joinEndDateLocal = joinDateEnd != null ? LocalDate.parse(joinDateEnd) : null;
        LocalDate expirationStartDateLocal = expirationDateStart != null ? LocalDate.parse(expirationDateStart) : null;
        LocalDate expirationEndDateLocal = expirationDateEnd != null ? LocalDate.parse(expirationDateEnd) : null;

        return memberRepository.findMembersByCriteria(idInt, firstN, lastN, username, email, phone, address,
                dobStartDate, dobEndDate, ageInt, type, durationInt, joinStartDateLocal,
                joinEndDateLocal, expirationStartDateLocal, expirationEndDateLocal, status);
    }

    public Member updateMember(int id, Member memberDetails) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setFirstName(memberDetails.getFirstName());
                    member.setLastName(memberDetails.getLastName());
                    member.setEmail(memberDetails.getEmail());
                    member.setUsername(memberDetails.getUsername());
                    member.setPhoneNumber(memberDetails.getPhoneNumber());
                    member.setLocationAddress(memberDetails.getLocationAddress());
                    member.setDateOfBirth(memberDetails.getDateOfBirth());
                    member.setJoinDate(memberDetails.getJoinDate());
                    member.setMembershipType(memberDetails.getMembershipType());
                    member.setMembershipDuration(memberDetails.getMembershipDuration());

                    // Calculate age from date of birth
                    int ageCalculated = MemberUtils.calculateAge(member.getDateOfBirth());
                    member.setAge(ageCalculated);

                    // Calculate expiration date from join date and membership duration
                    LocalDate expirationDateCalculated = MemberUtils.calculateExpirationDate(member.getJoinDate(),
                            member.getMembershipDuration());
                    member.setExpirationDate(expirationDateCalculated);

                    // Determine membership status based on expiration date
                    String statusDecided = MemberUtils.getMembershipStatus(member.getExpirationDate());
                    member.setStatus(statusDecided);

                    return memberRepository.save(member);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id " + id));
    }

    public String uploadMemberImage(int memberId, MultipartFile file) {
        String fileName = imageService.uploadImage(file, String.valueOf(memberId));

        // Update the member's profile picture URL in the database
        memberRepository.findById(memberId).ifPresent(member -> {
            member.setProfilePictureUrl(fileName);
            memberRepository.save(member);
        });

        return fileName;
    }

    public byte[] getMemberImageById(int memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            String fileName = memberOptional.get().getProfilePictureUrl();
            return imageService.getImage(fileName);
        } else {
            throw new ResourceNotFoundException("Member not found with id " + memberId);
        }
    }

}
