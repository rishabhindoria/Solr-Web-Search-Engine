var express = require('express');
var app = express();
var cors = require('cors');
app.use(cors());

var SolrNode = require('solr-node');
var client = new SolrNode({
    host: '127.0.0.1',
    port: '8983',
    core: 'rishabh_example',
    protocol: 'http'
});

var fs = require('fs'); 
var parse = require('csv-parse');
var HashMap = require('hashmap');
var map = new HashMap();

fs.createReadStream("/home/rishabh/Downloads/solr-6.5.0/mapNBCNewsDataFile.csv")
    .pipe(parse({delimiter: ','}))
    .on('data', function(csvrow) {
        map.set(csvrow[0],csvrow[1]);       
    });
	


app.get('/Go', function(req, res) {
	var query;
	if(req.query.choice.match("lucene"))
	{
		query='fl=id,description,title&indent=on&q='+req.query.search+'&wt=json&start=0&rows=10'; 
	}
	else if(req.query.choice.match("pagerank"))
	{
		query='fl=id,description,title&indent=on&q='+req.query.search+'&sort=pageRankFile%20desc&wt=json&start=0&rows=10'; 
	}
	client.search(query, function (err, result) {
		if (err) {
			console.log(err);
			return;
		}
		var output=[];
		output[0]=result.response.numFound;
		output[1]=[];
		result.response.docs.forEach(function(x){
			var id=x.id;
			var ans={"title":x.title,
					 "description":x.description,
					 "id":x.id,
					 "site_url":map.get(id.substring(id.lastIndexOf("/")+1))
					 }
			output[1].push(ans);
		});
		res.send(output);
	});		
});

app.listen(1337);
console.log("listening on 1337");