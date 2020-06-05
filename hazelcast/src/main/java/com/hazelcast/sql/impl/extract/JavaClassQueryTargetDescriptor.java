/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.sql.impl.extract;

import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.query.impl.getters.Extractors;

import java.io.IOException;

public class JavaClassQueryTargetDescriptor implements QueryTargetDescriptor {

    private String className;

    public JavaClassQueryTargetDescriptor() {
        // No-op.
    }

    public JavaClassQueryTargetDescriptor(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public QueryTarget create(InternalSerializationService serializationService, Extractors extractors, boolean isKey) {
        return new JavaClassQueryTarget(className, serializationService, extractors, isKey);
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(className);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        className = in.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JavaClassQueryTargetDescriptor that = (JavaClassQueryTargetDescriptor) o;

        return className.equals(that.className);
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    @Override
    public String toString() {
        return "JavaClassQueryTargetDescriptor{className=" + className + '}';
    }
}