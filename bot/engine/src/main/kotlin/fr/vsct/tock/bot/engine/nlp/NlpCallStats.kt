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

package fr.vsct.tock.bot.engine.nlp

import fr.vsct.tock.bot.definition.Intent
import fr.vsct.tock.bot.definition.IntentAware
import fr.vsct.tock.nlp.api.client.model.NlpIntentQualifier

/**
 * Stats about nlp call.
 */
data class NlpCallStats(val intent: Intent = Intent.unknown,
                        val intentProbability: Double?,
                        val entitiesProbability: Double?,
                        val otherIntentsProbabilities: List<NlpIntentStat>,
                        var intentsQualifiers: List<NlpIntentQualifier>? = null) {

    fun hasIntent(intent: IntentAware, minProbability: Double = 0.0): Boolean
            = otherIntentsProbabilities.any { intent.wrap(it.intent) && it.probability > minProbability }
}