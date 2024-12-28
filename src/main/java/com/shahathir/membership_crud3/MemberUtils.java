package com.shahathir.membership_crud3;

import java.time.LocalDate;
import java.time.Period;

// Class for utility methods for calculation and logic
public class MemberUtils {
    // Calculates the age of a member based on their date of birth
    public static int calculateAge(LocalDate dateOfBirth) {
        LocalDate dob = dateOfBirth;
        LocalDate currentDate = LocalDate.now();
        // Calculate the period between date of birth and current date, then return the years
        return Period.between(dob, currentDate).getYears();
    }

    // Calculates the expiration date of a membership based on join date and duration
    public static LocalDate calculateExpirationDate(LocalDate joinDate, int membershipDuration) {
        // Add the membership duration (in months) to the join date
        LocalDate expirationDate = joinDate.plusMonths(membershipDuration);
        return expirationDate;
    }

    // Determines the membership status based on the expiration date
    public static String getMembershipStatus(LocalDate expirationDate) {
        LocalDate currentDate = LocalDate.now();
        // If the expiration date is in the future, the membership is active
        if (expirationDate.isAfter(currentDate)) {
            return "Active";
        } else {
            // If the expiration date has passed, the membership is inactive
            return "Inactive";
        }
    }
}



