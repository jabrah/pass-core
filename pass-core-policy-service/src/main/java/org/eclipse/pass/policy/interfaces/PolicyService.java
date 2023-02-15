/*
 * Copyright 2022 Johns Hopkins University
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
package org.eclipse.pass.policy.interfaces;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.eclipse.pass.object.model.Policy;
import org.eclipse.pass.object.model.Repository;

/**
 * Represents PolicyService interface.
 * Provides blueprint for business logic.
 *
 * @author David McIntyre
 */
public interface PolicyService {
    /**
     * findPolicies()
     * Receives a submission URI and a map of headers. Resolves a list of relevant
     * Policies based on the context created by a submission against a set of DSL
     * rules.
     *
     * @param submission - PASS Submission URI
     * @param headers    - map of submission headers
     * @return List&lt;Policy&gt; - the list of applicable policies
     * @throws RuntimeException - submission could not be resolved to a list of
     *                          policies
     */
    public List<Policy> findPolicies(String submission, Map<String, String> headers) throws RuntimeException;

    /**
     * findRepositories
     * Receives a submission URI and a map of headers. Resolves a list of relevant
     * Repositories based on the context created by a submission against a set of
     * DSL rules.
     *
     * @param submissionURI - PASS submission URI
     * @param headers       - map of submission headers
     * @return List&lt;Repository&gt; - the list of applicable repositories
     * @throws RuntimeException - the submission could not be resolved to a list of
     *                          repositories
     */
    public List<Repository> findRepositories(URI submissionURI, Map<String, Object> headers) throws RuntimeException;
}
