Main class to start the program at: /src/test_program/Default_StartBrowser.java
1. At start a batch is running to list the browsers installed on this machine.
2. This list is used in dialog Combo where tester is asked to choose a browser to test the resourse on.
3. When browser is selected, the program visits subsequetly all the entries in the navigation drop menus.
4. If on entry's clicking the page opens as expected, the test is flagged as "passed", otherwise as "failed".
5. Page's title and URL pass to Extent Report module to be displayed in the report.
6. Following the Extent Report, screen shot of the page is performed.
7. At the end of testing process, or on its aborting by tester, Extent Report and screen shots are represented in two HTML files.
8. The files are displayed in two additional tabs in the same window where the tes run.
