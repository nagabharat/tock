/*
 * Copyright (C) 2017 VSCT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.vsct.tock.nlp.front.client

import com.github.salomonbrys.kodein.instance
import fr.vsct.tock.nlp.front.shared.ApplicationCodec
import fr.vsct.tock.nlp.front.shared.ApplicationConfiguration
import fr.vsct.tock.nlp.front.shared.ApplicationMonitor
import fr.vsct.tock.nlp.front.shared.ModelTester
import fr.vsct.tock.nlp.front.shared.ModelUpdater
import fr.vsct.tock.nlp.front.shared.Parser
import fr.vsct.tock.nlp.front.shared.codec.alexa.AlexaCodec
import fr.vsct.tock.shared.injector

private val parser: Parser by injector.instance()
private val applicationConfiguration: ApplicationConfiguration by injector.instance()
private val modelUpdater: ModelUpdater by injector.instance()
private val applicationCodec: ApplicationCodec by injector.instance()
private val alexaCodec: AlexaCodec by injector.instance()
private val applicationMonitor: ApplicationMonitor by injector.instance()
private val modelTester: ModelTester by injector.instance()

/**
 *
 */
object FrontClient :
        Parser by parser,
        ApplicationConfiguration by applicationConfiguration,
        ModelUpdater by modelUpdater,
        ApplicationCodec by applicationCodec,
        ApplicationMonitor by applicationMonitor,
        AlexaCodec by alexaCodec,
        ModelTester by modelTester