Login and Authentication Process Flow
This document outlines the detailed process flow for user login and authentication in a banking-related application, incorporating username/user ID as the primary login identifier, forced password change, security questions setup, password policy enforcement, password history management, and password lifetime configuration. Email is used for password reset or secondary purposes to enhance security.
Process Flow
1. User Initiates Login

Step 1.1: The user navigates to the login page of the system.
Step 1.2: The user enters their User ID (auto-generated or customized during registration, unique to the user) and password (sent through a secure delivery channel, e.g., email or SMS).
Step 1.3: The system validates the User ID and password against the stored credentials in the database.
If valid: Proceed to Step 2.
If invalid: Display a generic error message ("Invalid credentials") to avoid revealing specific issues (e.g., whether the User ID or password was incorrect) and prompt the user to try again or use the "Forgot Login Details" link (see Step 7).



2. Check for First-Time Login

Step 2.1: The system checks if this is the user's first login attempt by querying the user's account status (e.g., a flag indicating first login).
If first login: Proceed to Step 3 (Forced Password Change).
If not first login: Proceed to Step 5 (Authenticate and Grant Access).



3. Forced Password Change

Step 3.1: The system prompts the user to change their password since it is their first login.
Step 3.2: The user enters the temporary password (sent via the secure delivery channel, e.g., email or SMS) and defines a new password.
Step 3.3: The system validates the new password against the Password Policy:
Minimum Length: At least 8 characters (configurable).
Complexity: Must include a combination of:
Uppercase letters (A-Z)
Lowercase letters (a-z)
Numbers (0-9)
Special characters (e.g., !, @, #, $, etc.)


Password History Check: The new password is compared against the stored password history to prevent reuse of previously used passwords (see Step 4 for details).
If valid: The new password is saved, encrypted (e.g., using bcrypt), in the database, and the temporary password is invalidated. Proceed to Step 3.4.
If invalid: Display an error message (e.g., "Password does not meet complexity requirements or was previously used") and prompt the user to try again.


Step 3.4: Update the password lifetime timestamp to track the validity period of the new password (see Step 6 for details).
Step 3.5: Proceed to Step 4 (Security Questions Setup).

4. Security Questions Setup (First Login Only)

Step 4.1: The system presents the user with a list of five predefined security questions (e.g., "What is your mother's maiden name?", "What was the name of your first pet?", etc.).
Step 4.2: The user selects and answers three out of five questions.
Step 4.3: The system validates that all three responses are provided and meet basic requirements (e.g., non-empty responses).
If valid: The responses are stored securely (encrypted, e.g., using AES) in the database for use in password self-reset (see Step 7).
If invalid: Display an error message (e.g., "Please answer all three questions") and prompt the user to try again.


Step 4.4: Proceed to Step 5 (Authenticate and Grant Access).

5. Authenticate and Grant Access

Step 5.1: The system checks the password lifetime to ensure the password is still valid.
If within validity period: Proceed to Step 5.2.
If expired: Prompt the user to reset their password (similar to Step 3.2–3.4) and update the password lifetime timestamp.


Step 5.2: The system authenticates the user and grants access to the system dashboard or homepage.
Step 5.3: A session is created with a secure token (e.g., JWT with short expiry), and the user is logged in. Multi-factor authentication (MFA, e.g., SMS or app-based) is enforced to enhance security.

6. Password History and Lifetime Management

Password History:
The system maintains a record of previously used passwords (e.g., the last 5 passwords, configurable).
Each new password is checked against this history during password changes (Step 3.3).
Old passwords are stored securely (hashed) and cannot be reused.


Password Lifetime:
Passwords have a configurable validity period (e.g., 90 days).
The system tracks the creation date of each password.
Upon login, the system checks if the password has expired (Step 5.1).
If expired, the user is prompted to create a new password following the password policy (Step 3.3).



7. Password Self-Reset via Forgot Login Details

Step 7.1: The user clicks the "Forgot Login Details" link on the login page.
Step 7.2: The system prompts the user to enter their User ID.
Step 7.3: If the User ID is valid, the system retrieves the user's stored security questions and presents the three questions answered during setup (Step 4). Additionally, a verification code is sent to the user's registered email address or phone number (via secure delivery channel).
Step 7.4: The user provides answers to the three security questions and enters the verification code.
Step 7.5: The system validates the answers and the verification code against the stored responses and sent code.
If valid: The user is prompted to enter a new password, which must comply with the password policy (Step 3.3) and pass the password history check.
If invalid: Display a generic error message (e.g., "Incorrect answers or verification code") and allow a limited number of retry attempts (e.g., 3) before temporarily locking the account.


Step 7.6: Upon successful password reset, the new password is saved, the password lifetime is updated, and the user is redirected to the login page to log in with the new credentials.

Additional Notes

Security Considerations:
User IDs are auto-generated during registration (e.g., based on account number or name) or customizable, ensuring uniqueness and avoiding personal identifiers like email.
All passwords and security question responses are stored in encrypted form (e.g., bcrypt for passwords, AES for security answers).
The system enforces rate-limiting on login attempts and password reset attempts to prevent brute-force attacks.
MFA (e.g., SMS, app-based, or biometrics) is mandatory for all logins to comply with banking security standards.
Session tokens are secure, time-bound, and expire after a configurable period of inactivity.


Configurability:
Password policy parameters (minimum length, complexity requirements) are configurable by system administrators.
Password lifetime duration (e.g., 90 days) is configurable.
The number of stored passwords in the password history (e.g., 5) is configurable.
User ID generation rules (e.g., length, format) are configurable.


Delivery Channel:
Temporary passwords and verification codes are sent securely via the designated delivery channel (e.g., email or SMS) with a limited validity period (e.g., 24 hours for passwords, 10 minutes for codes).


User Experience:
User IDs are designed to be easy to use (e.g., short, memorable, or derived from account details).
Clear error messages and guidance are provided at each step, avoiding specific feedback (e.g., "User ID not found") to prevent information leakage.
Email is used for password reset and notifications but not as the primary login identifier to reduce exposure risks.


Banking Compliance:
The process adheres to banking security standards (e.g., PCI DSS, FDIC guidelines) by using usernames instead of emails, enforcing MFA, and securing sensitive data.
Account lockout mechanisms and audit trails are implemented to meet regulatory requirements.


