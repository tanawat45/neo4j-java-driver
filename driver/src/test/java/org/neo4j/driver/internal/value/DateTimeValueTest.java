/*
 * Copyright (c) 2002-2018 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.driver.internal.value;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.neo4j.driver.internal.types.InternalTypeSystem;
import org.neo4j.driver.v1.exceptions.value.Uncoercible;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DateTimeValueTest
{
    @Test
    public void shouldHaveCorrectType()
    {
        ZonedDateTime dateTime = ZonedDateTime.of( 1991, 2, 24, 12, 0, 0, 999_000, ZoneOffset.ofHours( -5 ) );
        DateTimeValue dateTimeValue = new DateTimeValue( dateTime );
        assertEquals( InternalTypeSystem.TYPE_SYSTEM.DATE_TIME(), dateTimeValue.type() );
    }

    @Test
    public void shouldSupportAsObject()
    {
        ZonedDateTime dateTime = ZonedDateTime.of( 2015, 8, 2, 23, 59, 59, 999_999, ZoneId.of( "Europe/Stockholm" ) );
        DateTimeValue dateTimeValue = new DateTimeValue( dateTime );
        assertEquals( dateTime, dateTimeValue.asObject() );
    }

    @Test
    public void shouldSupportAsZonedDateTime()
    {
        ZonedDateTime dateTime = ZonedDateTime.of( 1822, 9, 24, 9, 23, 57, 123, ZoneOffset.ofHoursMinutes( 12, 15 ) );
        DateTimeValue dateTimeValue = new DateTimeValue( dateTime );
        assertEquals( dateTime, dateTimeValue.asZonedDateTime() );
    }

    @Test
    public void shouldNotSupportAsLong()
    {
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeValue dateTimeValue = new DateTimeValue( dateTime );

        try
        {
            dateTimeValue.asLong();
            fail( "Exception expected" );
        }
        catch ( Uncoercible ignore )
        {
        }
    }
}
