package com.KPPhaseTwo.slingshot.builder

import com.KPPhaseTwo.slingshot.model.Background
import com.KPPhaseTwo.slingshot.model.Scenario
import groovy.util.logging.Log4j

@Log4j
class BackgroundBuilder {

    def build(def backgroundText) {
        boolean parseBackground = false
        log.info "Background parsing started with data ${backgroundText}"
        def backgrounds = new ArrayList<Background>()
        def background = new Background()
        backgroundText?.eachLine { line ->
            // when the line starts with scenario, keep parsing
            // rest of the file should be completely scenarios only
            if (line?.trim()?.startsWith('Background:')) {
                log.info "Received a scenario"
                parseBackground = true
                background = new Background()
            }

            // keep parsing the data
            if (parseBackground) {
                background.definition << line
                background.parse(line)
                backgrounds << background
            }

        }
        log.info "Scenario parsing completed"
        backgrounds
    }

}
