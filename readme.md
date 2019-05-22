


bin\solr start
bin\solr delete -c e_documents
bin\solr create -c e_documents
rm server\solr\e_documents\conf\managed-schema
cp \lab\java\solr\solr-demo\schema.xml server\solr\e_documents\conf\
cp \lab\java\solr\solr-demo\solrconfig.xml server\solr\e_documents\conf\
bin\solr stop -all