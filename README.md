# SwiftLing App - Spring Cloud Config Server

## Overview
This project is a Spring Cloud Config Server that provides centralized configuration management for all microservices in the SwiftLing application. It retrieves configuration properties from a GitHub repository and serves them dynamically to client services.

## Prerequisites
Ensure the following dependencies are installed and configured:
- **Java 21 or later**
- **Maven 3.9+** (compatible with Java 21)

## Environment Variables
The following environment variables must be set for the application to function properly:

| Variable Name               | Description|
|-----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `SWIFTLING_GITHUB_URL`      | GitHub repository containing configuration files (Make sure to copy the files in the example configuration repository in to your own config repository or fork it) ---> ([Example Configuration Repository](https://github.com/CundullahT/swiftling-config-repo.git))|
| `SWIFTLING_GITHUB_USERNAME` | GitHub username for accessing the repository (You need to use your own username)|
| `SWIFTLING_GITHUB_PASSWORD` | GitHub fine-grained token or personal access token (not the actual password)|
| `SWIFTLING_KEY`             | Encryption key for encrypting/decrypting sensitive properties (you can use any value you want here, e.g. "my-key")|

## GitHub Authentication
This Config Server retrieves configurations from a GitHub repository. If you are not a direct contributor, you must fork the repository and create a personal version with the same files.

- **GitHub Fine-Grained Token Guide:**  
  [Introducing Fine-Grained Personal Access Tokens](https://github.blog/security/application-security/introducing-fine-grained-personal-access-tokens-for-github/)  
  [Permissions for Fine-Grained Tokens](https://docs.github.com/en/rest/authentication/permissions-required-for-fine-grained-personal-access-tokens?apiVersion=2022-11-28)

- **GitHub Personal Access Token Guide:**  
  [Managing Your Personal Access Tokens](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens)

**Note:** You do not need both types of tokens; one (either fine-grained or personal access token) with sufficient access is enough. Use the token as the value for `SWIFTLING_GITHUB_PASSWORD`.

## Running the Application
1. Clone the repository:
   ```sh
   git clone https://github.com/CundullahT/swiftling-config-service.git
   cd swiftling-config-service
   ```
2. Set the required environment variables (example for Linux/macOS):
   ```sh
   export SWIFTLING_GITHUB_URL=your_github_configuration_repository_url
   export SWIFTLING_GITHUB_USERNAME=your_github_username
   export SWIFTLING_GITHUB_PASSWORD=your_github_token
   export SWIFTLING_KEY=your_secret_key
   ```
   For Windows (Command Prompt):
   ```cmd
   set SWIFTLING_GITHUB_URL=your_github_configuration_repository_url
   set SWIFTLING_GITHUB_USERNAME=your_github_username
   set SWIFTLING_GITHUB_PASSWORD=your_github_token
   set SWIFTLING_KEY=your_secret_key
   ```
3. Build the project using Maven:
   ```sh
   mvn clean package
   ```
4. Run the Config Server:
   ```sh
   java -jar target/*.jar
   ```
   OR using Spring Boot:
   ```sh
   mvn spring-boot:run
   ```

## Default Configuration
- The Config Server runs on **port 8888**
- Provides configuration properties to all registered microservices
- Supports encryption and decryption of sensitive values through the endpoints /encrypt and /decrypt

## Configuration Endpoints
To retrieve configuration properties for a specific service, use the following endpoint format:

- **SwiftLing Phrase Service Configuration for the "local" Environment:**
  ```
  http://localhost:8888/swiftling-phrase-service/local
  ```
- **SwiftLing User Service Configuration for the "local" Environment:**
  ```
  http://localhost:8888/swiftling-user-service/local
  ```

## Encrypting and Decrypting Values
The Config Server allows encryption and decryption of sensitive properties using the `SWIFTLING_KEY`.

- **Encrypt a value:**
  ```
  POST http://localhost:8888/encrypt
  Body: the_value_you_want_to_encrypt
  ```
- **Decrypt a value:**
  ```
  POST http://localhost:8888/decrypt
  Body: the_value_encrypted_by_using_same_key_earlier
  ```

## Additional Information
- This application is built using **Spring Boot 3.4.4**
- Ensure that the correct GitHub credentials are provided before starting the server

## License
This project is licensed under [MIT License](LICENSE).
