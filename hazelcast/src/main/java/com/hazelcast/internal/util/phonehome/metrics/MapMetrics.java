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
package com.hazelcast.internal.util.phonehome.metrics;

import com.hazelcast.core.DistributedObject;

import java.util.Collection;

import com.hazelcast.instance.impl.Node;

public class MapMetrics {

    Node hazelcastNode;
    Collection<DistributedObject> maps;

    public MapMetrics(Node node) {
        hazelcastNode = node;
        findMaps();
    }

    private void findMaps() {
        maps = hazelcastNode.hazelcastInstance.getDistributedObjects();
        for (DistributedObject distributedObject : maps) {
            String serviceName = distributedObject.getServiceName();
            if (!serviceName.endsWith("mapService")) {
                maps.remove(distributedObject);
            }
        }

    }

    public int getMapCount() {
        return maps.size();
    }
}
