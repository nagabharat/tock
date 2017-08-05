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

package fr.vsct.tock.bot.definition

/**
 * Base implementation of [StoryDefinition].
 */
open class StoryDefinitionBase(override val id: String,
                               override val storyHandler: StoryHandler,
                               override val starterIntents: Set<Intent>,
                               override val intents: Set<Intent> = starterIntents,
                               override val steps: Set<StoryStep> = emptySet()) : StoryDefinition {

    constructor(id: String,
                storyHandler: StoryHandler,
                steps: Array<out StoryStep> = emptyArray(),
                starterIntents: Set<Intent>,
                intents: Set<Intent> = starterIntents
                )
            : this(id, storyHandler, starterIntents, intents, steps.toSet())

}