# JaDDoS - Simple java implementation of DoS attack!

### Usage:
Either you can build the project and use the jar file for the execution or the released artifacts can be donwload in the github release page and use that jar file for the execution.

If you are going to build from acratch, just navigate into the root directory and simpley type `mvn clean install` in a terminal and that will build the artifacts in the target folder. Use the fat jar instead of thin jar since thin jar do not include all the dependencies.

Use a terminal and type `java -jar <path to fat jar> --help`. This will privide an abstract idea on how to use the application.

Sample execution: `java -jar jaddos-1.0.0-SNAPSHOT.jar --host exammple.com --protocol udp`

I do not give a sh!t if you are using this in a bad intention and I'm not liable for any misconduct on this piece of code. My sole idea was to implement this just for educational purpose. Use wisely!