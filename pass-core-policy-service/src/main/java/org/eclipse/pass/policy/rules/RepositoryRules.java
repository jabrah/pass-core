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

import org.eclipse.pass.object.model.Repository;
import org.eclipse.pass.policy.interfaces.VariableResolver;

/**
 * Represents the RepositoryRules object
 * RepositoryRules resolves rulesets to return relevant policies or repositories
 * in
 * a repository.
 *
 * @author David McIntyre
 */
public class RepositoryRules {

    /**
     * Resolve interpolates any variables in a repository and resolves against a
     * ruleset
     * to a list of applicable Repositories.
     *
     * @param variables - the ruleset to be resolved against
     * @return List&lt;Repository&gt; - the List of resolved Repositories
     * @throws RuntimeException - Repository could not be resolved
     */
    public List<Repository> resolve(Repository repo, VariableResolver variables) throws RuntimeException {
        List<Repository> resolvedRepos = new ArrayList<Repository>();
        Repository repository = repo;

        if (Variable.isVariable(repository.getId().toString())) {

            // resolve repository ID/s
            List<String> resolvedIDs = new ArrayList<String>();

            try {
                resolvedIDs.addAll(variables.resolve(repository.getId().toString()));

                for (String id : resolvedIDs) {
                    Repository resolved = new Repository();
                    //URI uriID = new URI(id);
                    resolved.setId(Long.parseLong(id));

                    resolvedRepos.add(resolved);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("Could not resolve property ID " + repository.getId().toString(), e);
            }
        } else {
            resolvedRepos.add(repository);
        }

        return resolvedRepos;
    }

}
