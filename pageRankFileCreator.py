import networkx as nx
target = open("external_pageRankFile",'a')
G = nx.read_edgelist("C:\\Users\\risha\\OneDrive\\CSCI572\\HW4\\edgeList.txt",create_using=nx.DiGraph())
pr = nx.pagerank(G, alpha=0.85, personalization=None, max_iter=100, tol=1e-06, nstart=None, weight='weight', dangling=None)
for key,value in pr.iteritems():
        target.write("/home/rishabh/Downloads/solr-6.5.0/NBCNewsDownloadData/"+key)
        target.write('=')
        target.write(str(value))
        target.write('\n')
target.close()
