package com.shahathir.membership_crud3;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

	
/**
 * Repository interface for managing Member entities.
 */
public interface MemberRepository extends JpaRepository<Member, Integer>{

	@Query("SELECT m FROM Member m WHERE " +
		   "(:id IS NULL OR m.id = :id) AND " +
           "(:firstName IS NULL OR m.firstName LIKE %:firstName%) AND " +
           "(:lastName IS NULL OR m.lastName LIKE %:lastName%) AND " +
           "(:username IS NULL OR m.username LIKE %:username%) AND " +
           "(:email IS NULL OR m.emailAddress LIKE %:email%) AND " +
           "(:phone IS NULL OR m.phoneNumber LIKE %:phone%) AND " +
           "(:address IS NULL OR m.locationAddress LIKE %:address%) AND " +
           //"(:dob IS NULL OR m.dateOfBirth = :dob) AND " +
		   "(:dobStartDate IS NULL OR m.dateOfBirth >= :dobStartDate) AND " +
		   "(:dobEndDate IS NULL OR m.dateOfBirth <= :dobEndDate) AND " +
           "(:age IS NULL OR m.age = :age) AND " +
           "(:type IS NULL OR m.membershipType LIKE %:type%) AND " +
           "(:duration IS NULL OR m.membershipDuration = :duration) AND " +
           //"(:joinDate IS NULL OR m.joinDate = :joinDate) AND " +
		   "(:joinStartDate IS NULL OR m.joinDate >= :joinStartDate) AND " +
		   "(:joinEndDate IS NULL OR m.joinDate <= :joinEndDate) AND " +
		   //"(:expirationDate IS NULL OR m.expirationDate = :expirationDate) AND " +
           "(:expirationStartDate IS NULL OR m.expirationDate >= :expirationStartDate) AND " +
		   "(:expirationEndDate IS NULL OR m.expirationDate <= :expirationEndDate) AND " +
           "(:status IS NULL OR m.status = :status)")
    List<Member> findMembersByCriteria(
		@Param("id") Integer id,
        @Param("firstName") String firstName,
        @Param("lastName") String lastName,
        @Param("username") String username,
        @Param("email") String email,
        @Param("phone") String phone,
        @Param("address") String address,
        //@Param("dob") LocalDate dob,
		@Param("dobStartDate") LocalDate dobStartDate,
		@Param("dobEndDate") LocalDate dobEndDate,
        @Param("age") Integer age,
        @Param("type") String type,
        @Param("duration") Integer duration,
        //@Param("joinDate") LocalDate joinDate,
		@Param("joinStartDate") LocalDate joinStartDate,
		@Param("joinEndDate") LocalDate joinEndDate,
        //@Param("expirationDate") LocalDate expirationDate,
		@Param("expirationStartDate") LocalDate expirationStartDate,
		@Param("expirationEndDate") LocalDate expirationEndDate,
        @Param("status") String status
    );

	//findByOR
	List<Member> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
	List<Member> findByFirstNameContainingOrLastNameContainingOrMembershipType(String firstName, String lastName, String membershipType);
	
	//findByAND
	List<Member> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName);
	List<Member> findByFirstNameContainingAndLastNameContainingAndMembershipTypeContaining(String firstName, String lastName, String membershipType);
	List<Member> findByFirstNameContainingAndLastNameContainingAndMembershipTypeContainingAndUsernameContaining(String firstName, String lastName, String membershipType, String username);
	List<Member> findByFirstNameContainingAndLastNameContainingAndMembershipTypeContainingAndUsernameContainingAndEmailAddressContaining(String firstName, String lastName, String membershipType, String username, String emailAddress);
	List<Member> findByFirstNameContainingAndLastNameContainingAndMembershipTypeContainingAndUsernameContainingAndEmailAddressContainingAndPhoneNumberContaining(String firstName, String lastName, String membershipType, String username, String emailAddress, String phoneNumber);
	List<Member> findByFirstNameContainingAndLastNameContainingAndMembershipTypeContainingAndUsernameContainingAndEmailAddressContainingAndPhoneNumberContainingAndLocationAddressContaining(String firstName, String lastName, String membershipType, String username, String emailAddress, String phoneNumber, String locationAddress);
	List<Member> findByFirstNameContainingAndLastNameContainingAndMembershipTypeContainingAndUsernameContainingAndEmailAddressContainingAndPhoneNumberContainingAndLocationAddressContainingAndStatusContaining(String firstName, String lastName, String membershipType, String username, String emailAddress, String phoneNumber, String locationAddress, String status);
	// List<Member> findBy(Member member);

	//findBy
	List<Member> findByFirstName(String firstName);
	List<Member> findByLastName(String lastName);
	List<Member> findByEmailAddress(String emailAddress);
	List<Member> findByUsername(String username);
	List<Member> findByPhoneNumber(String phoneNumber);
	List<Member> findByLocationAddress(String locationAddress);
	List<Member> findByDateOfBirth(LocalDate dateOfBirth);
	List<Member> findByAge(int age);
	List<Member> findByMembershipType(String membershipType);
	List<Member> findByMembershipDuration(int membershipDuration);
	List<Member> findByJoinDate(LocalDate joinDate);
	List<Member> findByExpirationDate(LocalDate expirationDate);
	List<Member> findByStatus(String status);

 	//findByContaining
	List<Member> findByFirstNameContaining(String firstName);
	List<Member> findByLastNameContaining(String lastName);
	List<Member> findByEmailAddressContaining(String emailAddress);
	List<Member> findByUsernameContaining(String username);
	List<Member> findByPhoneNumberContaining(String phoneNumber);
	List<Member> findByLocationAddressContaining(String locationAddress);
	List<Member> findByMembershipTypeContaining(String membershipType);
	List<Member> findByStatusContaining(String status);

	//findBetweenDates
	List<Member> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
	List<Member> findByJoinDateBetween(LocalDate startDate, LocalDate endDate);
	List<Member> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate);


}
