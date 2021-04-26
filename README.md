# GDPR Shield
Recruiters are not shy when adding YOU to their agencies databases.  
A recruiter can only add you to their database if you explicitly consent to their GDPR fine print.  
Did you ever waste a single second reading their privacy clauses? Neither have I.

GDPR gives every European the right to have their personal data erased from any information processing system.
So in case any recruiter bothers you by calling you or by emailing you, you can demand to be deleted from their database.

Agencies create amazing noise on the job market, thereby obfuscating skilled workers from companies - and vice versa.
Because I personally have strong belief, that the recruiting business as it's done today is parasitic and harmful,
I do not want to become a commodity for any agency.


### Who is this for ..
.. it's for freelancers and permanent employees who just switched jobs.

I just spend some weeks applying for all sorts of jobs - most of my applications were digested by recruiting agencies.
Now I get up to several eMails a day with ill-fitting job baits. The only thing worse than eMails are phone calls,
especially when they start with 'you received an email from us two days ago, and you didn't respond'.

So - if you're fed up with all of this none-sense, and you're looking for a way out - here's how:

* Forward recruiting spam to gdpr@basedefender.de (or dsgvo@basedefender.de)
* The service automatically sends a 'cease and delete all my personal data according to GDPR' eMail*
* It tracks the status for you

(* see for template: https://www.datarequests.org/blog/sample-letter-gdpr-erasure-request/)

If any agency has not implemented GDPR rules properly, and you continue to receive letters from them - now you will notice.
Once you notice, you can enforce your rights. The service helps you to significantly reduce the noise those agencies create.
It helps to keep your eMails spam free and your phone silent.


### How to use it
Just forward the eMail to gdpr@basedefender.de (or dsgvo@basedefender.de). 
Please ensure, that the eMail body contains information in the given format:

```
From: Laurem Ipsum <laurem.ipsum@agency.com>
Subject: Senior Java Freelancer - 100% Remote
Date: 23. April 2021 at 12:07:46 CEST
To: some.developer@gmail.com
```

Sometimes the eMail headers get lost, sometimes there's a whole chain of forwarding happening. 
That's why we need the information included on top of the eMail body.

## GDPR at basedefender.de
We use your eMail address as single reference.  
We do not log, track or do anything other with the information you supply us than taking 
care that a recruiter won't bother you in the future.

You can delete everything that's associated with your eMail address yourself! Amazing, right.  
See the next chapter and request a termination link. 

### Delete me!

Just go here (work-in-progress) and request a termination link.  
You're going to receive an eMail with a link. The link contains a token. Once you click it, all your data is scraped
off of our systems.

It's secure, because only you can access your eMail.   
No need for an extra account.    
No need to beg us to delete your data. 

We're better off without it.

# Self hosting

Build the container and run it    

    docker rm build -t gdpr-shield .
    docker run -it --rm -p 44301:44301 --name gdpr-shield gdpr-shield


or pull the current version from Docker Hub

    docker pull nykon/gdpr-shield











