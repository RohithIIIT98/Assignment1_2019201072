# Implemented Functionalites in clean code

## modified code

### Made Inner Classes
Actually found that args is doing many tasks such as parsing the schema and parsing the arguments that are passed by user
so to maintain modularity i wrote two inner classes named getKeys and assignKeysToValues

```bash
class outerClass{
  class InnerClass1{
  }
  class InnerClass2{
  }
}
```
### Passing String Instead of Iterator
In orginal uncle bob code we are passing Iterator<string> to all classes that implements ArgumentMarshaler.This makes possible
the other classes to get all other values of all positions.we must only send the string value instead of iterator.Now extra 
validation is also done to check .hasnext() if not exists then we are throwing error corrsponding to it
  
```bash
before used as argument:
Iterator<String>iterator;

after used as argument:
 String Parameter;
```
### Is Valid Function
This is used in all submodules of all classes that implements ArgumentMarshaler.It validates that it is instance of Argument
Marshaler and returns true else false
```bash
if(am!=NULL && am isInstanceof ArgumentMarshaler){
  return true
}
else{
 return false
}
```
### Try and catch as per rules of cleancode in submodules
Try catch are modularized.And kept in seperate functions


## implemented testcases

### Exception for repeatedkey
for Repeated key we used a map to store the element id  and element tail.And we they are repeated we throw exception related to it as REPEAT_KEY
```bash
Type:"p*,p#"
Here p has been defined 2 operations we are not allowing it
```
### Exception for Extra arguments
for one element id one value must be there or else error is thrown and handled
```bash
Type:
schema:"x*"
string:"-x 10 100"
```
### Exception for missing Key
if key is missed we are comparing with null and returning it.
```bash
Type:
schema:"x&"
string:"-x key1:val1,:val2"
```

## Characteristics of clean code
#### Naming Conventions:
clear and understandable to naive
#### Methods and functions:
modular and clear and each function takes only less than 3 arguments
#### Filestructure:
clean and more understandable
#### Indentation:(Ctrl+Alt+L):
In IntelliJ makes clear spaces and nested code
#### Avoding self explanotory comments


## SUBMITTED BY:
[SAI ROHITH ARETI]
[2019201072]
[M.Tech (C.S.E)]
