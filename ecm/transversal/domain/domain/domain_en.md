# Domain

[:es:](domain.md)

As it mentioned above this submodule is responsible to manage the domains for permit a correct maintenance of there.

- [Domain](#domain)
  - [Properties of domain](#properties-of-domain)
  - [Functionalities](#functionalities)
    - [Create domain](#create-domain)
    - [Modify domain](#modify-domain)
    - [Enable/Disable domain](#enabledisable-domain)
    - [Get all domains](#get-all-domains)
    - [Get domain](#get-domain)
    - [Delete domain](#delete-domain)
  - [Exceptions](#exceptions)
  - [Security](#security)

## Properties of domain
The domain contains de follow information:
- domain_id: Identify of domain, this value is unique, number and autoincremental.
- code_domain: Identify a domain through a name, this may be unique, the size is between 3 and 10 alphanumerics characters, the property is required and when the domain will be create this value cannot be modified.
- description: This property contain a describe of domain, this values is optional and at the moment only will be available in one language.
- enable: The last property is used to indicate whether or not we can use the domain, this property is a boolean type, where the active value is 1 and disabled value is 0, the default value is active.

## Functionalities
To be able to manage correctly the domains this module realize the following tasks:

### Create domain
Create new domains in the system, the characteristics of the functionality are:
  - Input: 
    - For create a domain is need a code of domain and optional description of this.
  - Output: 
    - When the informations are valid and create a domain, return a information on this.
  - Validations:
    - The code of domain must be unique and not exist anymore with the same code of domain, if the validation fail then launch the exception EXIST_CODE_DOMAIN
  - Return codes:
    - When the domain is create the code of return is 201
    - When the validation fail then code of return is 409 and show the message with the exception EXIST_CODE_DOMAIN

### Modify domain
This functionality only allow modify the description of domain, because the code of domain cannot be modify, the characteristics of the functionality are:
  - Input: 
    - This function need two parameters:
      1. Identify and/or code of domain.
      2. New description of domain.
  - Validations:
    - Check the domain exists in the system, if not exists then launch a exception DOMAIN_NOT_FOUND.
  - Output: 
    - When the description is modify then return domain.
  - Return codes:
    - When domain is modify the code of return is 200.
    - If not exist domain return code 404 and show message of exception DOMAIN_NOT_FOUND.

### Enable/Disable domain
Enabling or disabling a domain depends on its current state, the characteristics of this function are: 
  - Input:
    - Identify and/or code of domain
  - Validation:
    - Check the domain exists in the system, if not exists then launch a exception DOMAIN_NOT_FOUND.
  - Process:
    - When modify a status of domain have to do the following tasks:
      1. Modify state the values of domain.
      2. Modify the state of domain.
  - Output:
    - Return the domain modified.
  - Return codes:
    - When the domain and values are change it state the code of return is 200.
    - If not exists domain the code of return is 404 and show message of exception DOMAIN_NOT_FOUND.

### Get all domains
Return all domain in the system, the functionality have multiple ways to use:
  1. All domains: Return all domains indistinctly state of these.
       - Input:
         - No exists requirements.
       - Output:
         - If find domain return a list with all of them.
         - If not find domain return empty list.
       - Return codes:
         - If return list with domains the code of return is 200.
         - If return empty list the code of return is 200 and show message of exception DOMAINS_EMPTY.
  
  2. All domain paged : Return a paged list with all domains indistinctly state of these.
     - Input: 
       - Size of elements in each page, the default the elements in page are 10.
       - Number of page to access, the default page is 0.
     - Output:
       - If find domains then return paged list with element with size and number of page request.
       - If find domains but not enough for number page request then return the last page with domains.
       - If find domains but not enough for size of page request then return the a list with domains found.
       - If not find domain return empty list.
     - Return codes:
       - If return list of domain the code of return is 200.
       - If return empty list the code return is 200 and show message of exception DOMAINS_EMPTY.

  3. Active domains: Return a list with all domains with its state is enable.
     - Input: 
       - No exists requirements.
     - Output:
       - If exists active domains, return list with these domains.
       - If not exists active domains, return empty list.
     - Return codes:
       - If return list of domain the code of return is 200.
       - If return empty list the code return is 200 and show message of exception NOT_EXISTS_ACTIVE_DOMAINS.
  
  4. Active domains paged: Return a paged list with all domains with its state is enable.
     - Input:
       - Size of elements in each page, the default the elements in page are 10.
       - Number of page to access, the default page is 0.
     - Output:
       - If find active domains then return paged list with element with size and number of page request.
       - If find active domains but not enough for number page request then return the last page with domains.
       - If find active domains but not enough for size of page request then return the a list with domains.
       - If not find active domains return empty list.
     - Return codes:
       - If return list of domain the code of return is 200.
       - If return empty list the code return is 200 and show message of exception NOT_EXISTS_ACTIVE_DOMAINS.

### Get domain
Return information about a domain, the characteristics of this function are:
  - Input:
    - Identify and/or code of domain.
  - Output:
    - If exists domain, return information about this.
    - If not exists domain, execute exception DOMAIN_NOT_FOUND
  - Return codes:
    - If return information about domain, the code of return is 200.
    - If execute exception DOMAIN_NOT_FOUND, the return code is 404 and show the message of exception

### Delete domain
Delete domain of the system, he characteristics of this function are:
  - Input: 
    - Identify and/or code of domain.
  - Validations: The domain will be delete if validate the following situations:
    1. Exists domain, if not exists execute exception DOMAIN_NOT_FOUND.
    2. The domain haven't associates values. 
    3. If values exists in the domain, these values mustn't be in use, if values are in use then execute exception VALUES_DOMAIN_IN_USE_ERROR.
  - Process: When the validation process is correct then executes the following steps for delete domain:
    1. Remove translates of the values.
    2. Remove the values in domain.
    3. Remove domain.
  - Output:
    - If domain is delete then code of return is 204.
    - If not exists domain then the code of return is 404 and show the message of exception DOMAIN_NOT_FOUND.
    - If any value of domain is in use then the code of return is 409 and show the message of exception VALUES_DOMAIN_IN_USE_ERROR.

## Exceptions
- DOMAINS_EMPTY: 
  - Not exists domains
- DOMAIN_NOT_FOUND: 
    - Not find domain with code %CODE_DOMAIN%
    - Not find domain with identify %DOMAIN_ID%
    - Not find domain with identify %DOMAIN_ID% and code %CODE_DOMAIN%
- EXISTS_CODE_DOMAIN: 
  - Exists domain with code %CODE_DOMAIN%
- NOT_EXISTS_ACTIVE_DOMAINS: 
  - Not exists active domains.
- VALUES_DOMAIN_IN_USE_ERROR: 
  - Domain with code %CODE_DOMAIN% contain values in use.

## Security
For use this module, users must be logging and have rol domain_admin.