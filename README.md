# Simple_Banking_System
### The objectives of this project:
1. Connect to the database (the database name is passed as an argument).
2. Create table "card" if it doesn't exist.
3. Generate a bank card number using the Luhn algorithm (the bank card number must start with 4000 00).
4. Generate a random pin.
5. Create a menu for the user, where you can:
- Check balance;
- Add amount;
- Do transfer;
- Close your account.
6. When doing transfer, process errors if you enter:
- Your bank card number;
- Bank card number that does not correspond to the Luhn algorithm;
- A bank card number that does not exist;
- The transfer amount is greater than the user's current balance.
### Examples:
```
1. Create account
2. Log into account
0. Exit
>1

Your card has been created
Your card number:
4000008062833053
Your card PIN:
6521

1. Create account
2. Log into account
0. Exit
>1

Your card has been created
Your card number:
4000001016025660
Your card PIN:
8789

1. Create account
2. Log into account
0. Exit
>admin

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>1

id        number              pin       balance   
1         4000008062833053    6521      0         
2         4000001016025660    8789      0         

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>0

You have exited the secret menu!

1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000008062833053
Enter your PIN:
>4444

Wrong card number or PIN!

1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000008062833053
Enter your PIN:
>6521

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 0

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>2

Enter income:
>10000
Income was added!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 10000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000008062833053
You can't transfer money to the same account!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000008062833052
Probably you made a mistake in the card number. Please try again!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000001016025660
Enter how much money you want to transfer:
>12000
Not enough money!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>3

Transfer
Enter card number:
>4000001016025660
Enter how much money you want to transfer:
>2000
Success!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>1

Balance: 8000

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>5

You have successfully logged out!

1. Create account
2. Log into account
0. Exit
>admin

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>1

id        number              pin       balance   
1         4000008062833053    6521      8000      
2         4000001016025660    8789      2000      

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>0

You have exited the secret menu!

1. Create account
2. Log into account
0. Exit
>0

Bye!
```
```
1. Create account
2. Log into account
0. Exit
>admin

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>1

id        number              pin       balance   
1         4000008062833053    6521      8000      
2         4000001016025660    8789      2000      

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>0

You have exited the secret menu!

1. Create account
2. Log into account
0. Exit
>2

Enter your card number:
>4000001016025660
Enter your PIN:
>8789

You have successfully logged in!

1. Balance
2. Add income
3. Do transfer
4. Close account
5. Log out
0. Exit
>4

The account has been closed!

1. Create account
2. Log into account
0. Exit
>admin

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>1

id        number              pin       balance   
1         4000008062833053    6521      8000      

Welcome! You have unlocked the secret menu!

1. Accounts
0. Back
>0

You have exited the secret menu!

1. Create account
2. Log into account
0. Exit
>0

Bye!
```
