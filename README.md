# hokodoQATest

## Prerequisites
1. Chrome or Safari installed on the machine - safari requires Webdriver to be enabled. To enable it, run `safaridriver --enable`. Safari tests can only be ran on macOS. 
2. Java and Maven installed on the machine 

## Test execution 
1. Clone the repo
2. To run the tests on Chrome, run `mvn test -DtargetBrowser=chrome` 
3. To run the tests on Safari, run `mvn test -DtargetBrowser=safari`

The tests will run on Chrome by default if no target is specified.

The report is generated in `/target/surefire-reports/index.html`
