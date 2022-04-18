# Design Notes

## Project Setup

Used maven to created the project.

```
mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=2.0.29
```

# Requirements

## Use symmetric cryptography for the PushRecalculateAndEncrypt and Decrypt APIs.
- Created a singleton class that handles key generation and encryption/decryption as well as encoding/decoding to/from base64.
- To allow for secure encription and proper decription a new IV is generated each time and is prepended to the encryption result and later retrieved for decryption.

### Provision only 1 key for your system
Assumption: they key is auto generated and can be reset when the application is restarted

Alternative: pre generate a key, save it to a config file and read it each time.

## Don't assume you have infinite RAM for storage of input numbers

- I made sure to optmize the calculations avoiding storing each provided input.
  - Made sure I understood the calculation by creating a [spreadsheet](https://docs.google.com/spreadsheets/d/1LAk32A5D16LFFauk8EKeIPL9flctDx2FLXcZsCecvrU/edit?usp=sharing) and solving them there first
  - Stored `rolling averrage` and `sum of squares` instead
- Made use of `BigDecimal` to avoid overflow, which could happen fairly quickly for `sum of squares`

# References

- [Lets Try DropWizard](https://www.youtube.com/watch?v=GjItN847lFs&t=483s&ab_channel=TechPrimers)
- [AES Encryption/Decryption](https://www.youtube.com/watch?v=J1RmZZEkN0k&ab_channel=WhiteBatCodes)
- [Rolling Average/Stard Deviation Spreadsheet](https://docs.google.com/spreadsheets/d/1LAk32A5D16LFFauk8EKeIPL9flctDx2FLXcZsCecvrU/edit?usp=sharing)
# Improvements
Possible improvements include:
- Set up Dependency Injection and/or apply the factory pattern
- Better Unit Test coverage