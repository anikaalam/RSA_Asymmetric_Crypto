Proj 1 - part 1

Warm up: 

Server Output:

Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ java SimpleServer
Server has started...
Client #1: Hello 
Client #2: I am worthy!
Client #3: Nope

Client Output: 

Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ java SimpleClient Anika 
Client Anika is here... 
Enter text: Hello 
Server replied: You send me -> HELLO . Your random # is: 11653

Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ java SimpleClient ThorClient Thor is here... 
Enter text: I am worthy!
Server replied: You send me -> I AM WORTHY!. Your random # is: 66412

Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ java SimpleClient LokiClient Loki is here... 
Enter text: Nope
Server replied: You send me -> NOPE. Your random # is: 11012


Part I 

- add confidentiality layer to the client-server comm
  using a shared symmetric key, 
	(i) generated by run the symetrickey.java file to produce 
	    base-64 key.txt file creates AES-key

- assum ekey lready shared in secured channel
- add AES encryption/decrytion funcitonalitie to both server and client profram 

Symmetric Output:

-> Server side 

Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ vim SymmetricKeyGenerator.java
Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ java SymmetricServer key.txt

Server has started...
Client #1: Hello I am Anika!

Anika@Fahmidas-MacBook-Pro:~/Desktop/net sec/Proj11-redo$ java SymmetricServerey22.txt

Server has started...
Client #1: Maybe my name is not Anika!

-> Client side 

Anika@Fahmidas-MBP:~/Desktop/net sec/Proj11-redo$ java SymmetricClient anika key.txt

Client anika is here... 
Enter text: Hello I am Anika!
Server replied: You send me -> HELLO I AM ANIKA!. Your random # is: 23377

Anika@Fahmidas-MBP:~/Desktop/net sec/Proj11-redo$ java SymmetricClient anika key22.txt

Client anika is here... 
Enter text: Maybe my name is not Anika!
Server replied: You send me -> MAYBE MY NAME IS NOT ANIKA!. Your random # is: 63031






Part 2: Sample output, comments in the code 

Anika@Fahmidas-MacBook-Pro:~/Desktop/AAA$ java AsymmetricServer privateKey.txt
Server has started...

Anika@Fahmidas-MacBook-Pro:~/Desktop/AAA$ java AsymmetricClient publicKey.txt
Client is here... 
Enter text: Hi this is the first message 
Server replied: Hi this is the first message   Goodbye!!

Anika@Fahmidas-MacBook-Pro:~/Desktop/AAA$ java AsymmetricServer privateKey.txt
Server has started...

Anika@Fahmidas-MacBook-Pro:~/Desktop/AAA$ java AsymmetricClient publicKey.txt
Client is here... 
Enter text: This is my second message 
Server replied: This is my second message   Goodbye!!





