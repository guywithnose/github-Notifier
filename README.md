github Notifier
==========================

This simple program utilizes the github API to check a repository
for new comments and notify the user when a new comment comes in.

Usage
-----

The first thing you need to do it create a github application.  This
can be found under account settings on github.

Once you create the application simply create a config file
(use config.example for template), and add in your client_id and client_secret.

To run the program you can just type ant run or ant package then run
java -jar build/githubNotifier.jar.  You can use this jar separately just
make sure you bring the config file in the same folder.

One the program loads click authorize and then copy the code that will
appear in your browser.  The next menu lists your repositories.  After you
select a repository, the program starts listening for new events.
