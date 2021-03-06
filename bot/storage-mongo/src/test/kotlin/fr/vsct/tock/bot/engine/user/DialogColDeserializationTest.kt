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

package fr.vsct.tock.bot.engine.user

import com.fasterxml.jackson.module.kotlin.readValue
import fr.vsct.tock.bot.definition.Intent
import fr.vsct.tock.bot.engine.dialog.ContextValue
import fr.vsct.tock.bot.engine.dialog.Dialog
import fr.vsct.tock.bot.engine.dialog.NextUserActionState
import fr.vsct.tock.bot.mongo.DialogCol
import fr.vsct.tock.bot.mongo.DialogCol.DialogStateMongoWrapper
import fr.vsct.tock.bot.mongo.DialogCol.EntityStateValueWrapper
import fr.vsct.tock.bot.mongo.UserTimelineCol
import fr.vsct.tock.nlp.api.client.model.Entity
import fr.vsct.tock.nlp.api.client.model.EntityType
import fr.vsct.tock.nlp.api.client.model.NlpIntentQualifier
import fr.vsct.tock.shared.jackson.AnyValueWrapper
import fr.vsct.tock.shared.jackson.mapper
import io.mockk.mockk
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.assertEquals

/**
 *
 */
class DialogColDeserializationTest : AbstractTest() {

    @Test
    fun serializeAndDeserializeAnyValueWrapper_shouldLeftDataInchanged() {
        val value = AnyValueWrapper(
                UserLocation::class,
                UserLocation(1.0, 2.0))
        val s = mapper.writeValueAsString(value)
        val newValue = mapper.readValue<AnyValueWrapper>(s)
        assertEquals(value, newValue)
    }

    @Test
    fun serializeAndDeserializeStateMongoWrapper_shouldLeftDataInchanged() {
        val state = DialogStateMongoWrapper(
                Intent("test"),
                mapOf("role" to EntityStateValueWrapper(
                        ContextValue(
                                0,
                                1,
                                Entity(EntityType("type"), "role"),
                                "content"
                        ),
                        emptyList()
                )),
                emptyMap(),
                UserLocation(1.0, 2.0),
                NextUserActionState(
                        listOf(NlpIntentQualifier("test")),
                        ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")),
                        ZoneId.systemDefault()
                )
        )
        val s = mapper.writeValueAsString(state)
        val newValue = mapper.readValue<DialogStateMongoWrapper>(s)
        assertEquals(state, newValue)
    }


    @Test
    fun serializeAndDeserializeDialog_shouldLeftDataInchanged() {
        val dialog = Dialog(
                emptySet(),
                state = fr.vsct.tock.bot.engine.dialog.DialogState(context = mutableMapOf("a" to LocalDateTime.now())))
        val playerId = PlayerId("a", PlayerType.user)
        val s = mapper.writeValueAsString(DialogCol(dialog, UserTimelineCol(UserTimeline(playerId), null)))
        val newValue = mapper.readValue<DialogCol>(s)
        assertEquals(dialog, newValue.toDialog { mockk() })
    }


}