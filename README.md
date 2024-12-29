Here is your corrected `README.md` file:

---

# CryptoAnalyzer

CryptoAnalyzer is a simple Java-based application that encodes, decodes, and decrypts text using brute force. It provides both a basic GUI and a console-based interface for user interaction.

## Features

- **Encode Text**: Encrypt a text file using a specified shift key.
- **Decode Text**: Decrypt an encrypted text file using the original shift key.
- **Brute Force**: Decrypt a text file without the key by analyzing patterns and comparing it against a sample text file.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Iskanderrus/CryptoAnalyzer.git
   ```

2. Navigate into the project directory:
   ```bash
   cd CryptoAnalyzer
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   - **Console mode**:
     ```bash
     java -cp target/classes ru.javarush.chasovskoy.cryptoanalyzer.ConsoleRunner
     ```
   - **GUI mode**:
     ```bash
     java -cp target/classes ru.javarush.chasovskoy.cryptoanalyzer.GuiRunner
     ```

## Usage

Follow the on-screen prompts for each mode:

- **Encode**: Provide the input file name, output file name, and encryption key as an integer.
- **Decode**: Provide the encrypted file name, output file name, and decryption key as an integer.
- **Brute Force**: Provide the encrypted file name and a sample text file name.

### Assumptions

The `texts` directory contains `text.txt` and `dict.txt` files. These files are used as input for encryption and brute force and as a sample for the brute force operation. To use your own files, place them in the `texts` directory and specify their names during operations.

### Example Usage for Console Runner

#### Encode:
```
Enter the input file path: text.txt  
Enter the output file path: encoded.txt  
Enter the key (integer): 5  
```

#### Decode:
```
Enter the input file path: encoded.txt  
Enter the output file path: decoded.txt  
Enter the key (integer): 5  
```

#### Brute Force:
```
Enter the input file path: encoded.txt  
Enter the sample text file path: sample.txt  
```

## Running the JAR File

You can also use the pre-built JAR files located in the `CryptoAnalyzer/out/artifacts` directory. Follow these steps:

1. Place the required JAR file in the same directory as the `texts` directory.
2. Run the JAR file:
   ```bash
   java -jar CryptoAnalyzer.jar
   ```

## Requirements

- Java 17 or later.
- Maven for building the project.