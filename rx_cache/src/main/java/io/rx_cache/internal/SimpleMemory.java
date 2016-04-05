/*
 * Copyright 2015 Victor Albertos
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

package io.rx_cache.internal;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleMemory implements Memory {
    private final ConcurrentMap<String, Record> concurrentMap;

    public SimpleMemory() {
        concurrentMap = new ConcurrentHashMap();
    }

    @Override public <T> Record<T> getIfPresent(String key) {
        return concurrentMap.get(key);
    }

    @Override public <T> void put(String key, Record<T> record) {
        concurrentMap.put(key, record);
    }

    @Override public Set<String> keySet() {
        return concurrentMap.keySet();
    }

    @Override public void evict(String key) {
        concurrentMap.remove(key);
    }

    @Override public void evictAll() {
        for (String key : keySet()) {
            concurrentMap.remove(key);
        }
    }
}
