package com.shahathir.membership_crud3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        // Prepare the member with calculated age, expiration date, and status
        Member preparedMember = memberService.prepareMemberForSave(member);

        // Save the member with the calculated age and membership expiration date
        return memberService.saveMember(preparedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member memberDetails) {
        try {
            Member updatedMember = memberService.updateMember(id, memberDetails);
            return ResponseEntity.ok(updatedMember);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable int id) {
        if (memberService.getMemberById(id).isPresent()) {
            memberService.deleteMember(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String firstN,
            @RequestParam(required = false) String lastN,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String dobStart,
            @RequestParam(required = false) String dobEnd,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String joinDateStart,
            @RequestParam(required = false) String joinDateEnd,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String expirationDateStart,
            @RequestParam(required = false) String expirationDateEnd) {

        return memberService.searchMembers(
                id, firstN, lastN, type, username,
                email, phone, address, dobStart, dobEnd, age,
                joinDateStart, joinDateEnd, status, duration, expirationDateStart, expirationDateEnd);
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<String> uploadMemberImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = memberService.uploadMemberImage(id, file);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getMemberImageById(@PathVariable int id) {
        try {
            byte[] imageData = memberService.getMemberImageById(id);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
