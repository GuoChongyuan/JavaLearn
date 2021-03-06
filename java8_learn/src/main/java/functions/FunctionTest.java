/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package functions;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import static com.google.common.base.Preconditions.checkNotNull;

public final class FunctionTest {

  public static Function<Object, String> INSTANCE = o ->{
    checkNotNull(o);
    return o.toString();
  };


  public static void main(String[] args) {
    String apply = INSTANCE.apply(Lists.newArrayList(1, 2));
    System.out.println(apply);
  }
}
