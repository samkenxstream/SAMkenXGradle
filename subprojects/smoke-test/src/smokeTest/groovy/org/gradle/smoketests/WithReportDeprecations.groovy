/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.smoketests

import groovy.transform.SelfType
import org.gradle.api.internal.DocumentationRegistry
import org.gradle.util.internal.VersionNumber

import static org.gradle.api.internal.DocumentationRegistry.RECOMMENDATION

@SelfType(BaseDeprecations)
trait WithReportDeprecations {
    private static final String REPORT_DESTINATION_DEPRECATION = "The Report.destination property has been deprecated. " +
        "This is scheduled to be removed in Gradle 9.0. " +
        "Please use the outputLocation property instead. " +
        String.format(RECOMMENDATION, "information", new DocumentationRegistry().getDslRefForProperty("org.gradle.api.reporting.Report", "destination"))

    void expectReportDestinationPropertyDeprecation(String agpVersion) {
        runner.expectDeprecationWarningIf(
            VersionNumber.parse(agpVersion).baseVersion < VersionNumber.parse("7.4.1"),
            REPORT_DESTINATION_DEPRECATION,
            "https://github.com/gradle/gradle/issues/21533"
        )
    }

    void maybeExpectReportDestinationPropertyDeprecation(String agpVersion) {
        runner.maybeExpectLegacyDeprecationWarningIf(
            VersionNumber.parse(agpVersion).baseVersion < VersionNumber.parse("7.4.1"),
            REPORT_DESTINATION_DEPRECATION
        )
    }
}
