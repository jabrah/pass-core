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
package org.eclipse.pass.policy.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.pass.object.model.Policy;
import org.eclipse.pass.policy.components.VariablePinner;
import org.eclipse.pass.policy.interfaces.PolicyResolver;

/**
 * Represents the DSL object
 * DSL encapsulates to a policy rules document
 *
 * @author David McIntyre
 */
public class DSL implements PolicyResolver {

    private PolicyRules policyRules;

    private String schema; // json:"$schema"
    private List<Policy> policies; // json:"policy-rules"

    /**
     * DSL.resolve()
     * Resolves a list of applicable Policies using a provided ruleset against a
     * database of policies that are instantiated at runtime.
     *
     * @param variables - the ruleset to be resolved against
     * @return List&lt;Policy&gt; - the List of resolved policies
     * @throws RuntimeException - Policy rule could not be resolved
     */
    @Override
    public List<Policy> resolve(VariablePinner variables) throws RuntimeException {
        List<Policy> resolvedPolicies = new ArrayList<Policy>();

        for (Policy policy : policies) {
            try {
                List<Policy> resolved = policyRules.resolve(policy, variables);

                // If a resolved policy or policies exist, append to final list
                if (resolved.size() > 0) {
                    resolvedPolicies.addAll(resolved);
                }

            } catch (RuntimeException e) {
                throw new RuntimeException("Could not resolve policy rule", e);
            }
        }

        return policyRules.uniquePolicies(resolvedPolicies);
    }
}
