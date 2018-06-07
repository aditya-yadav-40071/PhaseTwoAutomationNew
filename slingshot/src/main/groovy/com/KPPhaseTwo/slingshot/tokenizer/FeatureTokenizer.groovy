package com.KPPhaseTwo.slingshot.tokenizer

import groovy.util.logging.Log4j

@Log4j
class FeatureTokenizer {

    def tokenize(def feature) {
        log.info "Tokenizing the feature file started"
        def tokens = feature.tokenize()
        log.info "Tokens are :: ${tokens}"
        log.info "Tokenizing the feature file completed"
    }

}
