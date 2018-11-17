package com.intersysconsulting.nlpexamples;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;

import java.io.PrintStream;
import java.util.*;


public class BasicPipelineExample {

    public static String text = "Joe Smith was born in California. " +
        "In 2017, he went to Paris, France in the summer. " +
        "His flight left at 3:00pm on July 10th, 2017. " +
        "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
        "He sent a postcard to his sister Jane Smith. " +
        "After hearing about Joe's trip, Jane decided she might go to France one day.";

    static PrintStream p = System.out;

    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // create a document object
        CoreDocument document = new CoreDocument(text);
        // annnotate the document
        pipeline.annotate(document);
        // examples

        // 10th token of the document
        CoreLabel token = document.tokens().get(10);
        p.println("Example: token");
        p.println(token);
        p.println();

        // text of the first sentence
        String sentenceText = document.sentences().get(0).text();
        p.println("Example: sentence");
        p.println(sentenceText);
        p.println();

        // second sentence
        CoreSentence sentence = document.sentences().get(1);

        // list of the part-of-speech tags for the second sentence
        List<String> posTags = sentence.posTags();
        p.println("Example: pos tags");
        p.println(posTags);
        p.println();

        // list of the ner tags for the second sentence
        List<String> nerTags = sentence.nerTags();
        p.println("Example: ner tags");
        p.println(nerTags);
        p.println();

        // constituency parse for the second sentence
        Tree constituencyParse = sentence.constituencyParse();
        p.println("Example: constituency parse");
        p.println(constituencyParse);
        p.println();

        // dependency parse for the second sentence
        SemanticGraph dependencyParse = sentence.dependencyParse();
        p.println("Example: dependency parse");
        p.println(dependencyParse);
        p.println();

        // kbp relations found in fifth sentence
        List<RelationTriple> relations =
            document.sentences().get(4).relations();
        p.println("Example: relation");
        p.println(relations.get(0));
        p.println();

        // entity mentions in the second sentence
        List<CoreEntityMention> entityMentions = sentence.entityMentions();
        p.println("Example: entity mentions");
        p.println(entityMentions);
        p.println();

        // coreference between entity mentions
        CoreEntityMention originalEntityMention = document.sentences().get(3).entityMentions().get(1);
        p.println("Example: original entity mention");
        p.println(originalEntityMention);
        p.println("Example: canonical entity mention");
        p.println(originalEntityMention.canonicalEntityMention().get());
        p.println();

        // get document wide coref info
        Map<Integer, CorefChain> corefChains = document.corefChains();
        p.println("Example: coref chains for document");
        p.println(corefChains);
        p.println();

        // get quotes in document
        List<CoreQuote> quotes = document.quotes();
        CoreQuote quote = quotes.get(0);
        p.println("Example: quote");
        p.println(quote);
        p.println();

        // original speaker of quote
        // note that quote.speaker() returns an Optional
        p.println("Example: original speaker of quote");
        p.println(quote.speaker().get());
        p.println();

        // canonical speaker of quote
        p.println("Example: canonical speaker of quote");
        p.println(quote.canonicalSpeaker().get());
        p.println();

    }

}