# Solr-Web-Search-Engine

• Created a web search engine using Solr.

•	Initialised a core on Solr and indexed all the required html pages on which user would submit queries. 

• JSOUP java library was used to extract outgoing links from each web page indexed by Solr to create edgeList.txt file.

• edgeList.txt file was then passed as an input to pageRankFileCreator python code to generate pageRankFile to be used by Solr for ranking web pages according to query term submitted by the user.

•	Used jQuery ajax request to send the query submitted by user to the Nodejs server.

•	Solr-node Nodejs library was then used to intiate a request to Solr server.

•	Results returned by the Solr server in the form of JSON were sent as a reponse by Nodejs server back to the jQuery ajax call which were then parsed and displayed on the front end of the web application.
