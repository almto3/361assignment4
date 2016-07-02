UTEID: mp34495; soa322;
FIRSTNAME: Ovais; Saleh;
LASTNAME: Panjwani; Alghusson;
CSACCOUNT: mp34495; almto3;
EMAIL: ovais.panjwani@utexas.edu; almto3@hotmail.com;


[Program 4]
[Description]
There are 7 java files: 	In AES.java, we implemented the driver main method and some helpers to it.
							In Utility.java, we implemented each of the steps of the encryption/decryption process. Each in a different method, with some having companion helper methods.
							MixColumns.java, is pretty much the exact code provided to us by Dr. Young, with slight modifications.
							Constants.java, has no code, but rather some constant arrays that we got from the internet and the project specs.
							AddRoundKey.java, is responsible for key expansion.
							Reader.java, reads from a file.
							Writer.java, writes to a file.  
 We both worked together on this project, mostly on the same screen, however, we did work remotely for some parts, in those times, saleh worked on AES.java and Utility.java implementing most of it, while ovais finished working on and testing AddRoundKey.java.
 To compile the program, you need to use "javac *.java". To run our program, you need to use "java AES <e or d> <keyfile> <inputfile>"

[Finish]
We finished all of the requirements for this assignment. as far as I know, there are no bugs.


[Test Case 1]

[Command line]
e /Users/almto3/Github/361assignment4/keyFile /Users/almto3/Github/361assignment4/inputFile1
[Timing Information]
27 milsec
[Input Filenames]
inputFile1
[Output Filenames]
inputFile1.enc


[Test Case 2]

[Command line]
d /Users/almto3/Github/361assignment4/keyFile /Users/almto3/Github/361assignment4/inputFile1.enc
[Timing Information]
40 milsec
[Input Filenames]
inputFile1.enc
[Output Filenames]
inputFile1.enc.dec


[Test Case 3]

[Command line]
e /Users/almto3/Github/361assignment4/keyFile /Users/almto3/Github/361assignment4/inputFile2
[Timing Information]
72 milsec
[Input Filenames]
inputFile2
[Output Filenames]
inputFie2.enc


[Test Case 4]

[Command line]
d /Users/almto3/Github/361assignment4/keyFile /Users/almto3/Github/361assignment4/inputFile2.enc
[Timing Information]
62 milsec
[Input Filenames]
inputFile2.enc
[Output Filenames]
inputFile2.enc.dec


[Test Case 5]

[Command line]
e /Users/almto3/Github/361assignment4/keyFile /Users/almto3/Github/361assignment4/inputFile3
[Timing Information]
63 milsec
[Input Filenames]
inputFile3
[Output Filenames]
inputFie3.enc


[Test Case 6]

[Command line]
d /Users/almto3/Github/361assignment4/keyFile /Users/almto3/Github/361assignment4/inputFile3.enc
[Timing Information]
56 milsec
[Input Filenames]
inputFile3.enc
[Output Filenames]
inputFile3.enc.dec