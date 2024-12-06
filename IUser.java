package Database;

public interface IUser {
    public interface User {
        // Common getters for user attributes
        String getId();
        String getName();
        String getEmail();
        String getAddress();
    
        // Method to display user-specific information
        void displayAccountInfo();    
        // Method to get the role of the user (e.g., Customer, Staff)
        String getRole();
    }
}
