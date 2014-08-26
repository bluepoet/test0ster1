/**
 * Copyright 2014 Seong Hyun (Kevin)
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
package cc.kevinlee.testosterone;

import java.util.function.Consumer;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-08-26)
 *
 */
public class ThenAfterCalling<T> implements Then<Consumer<T>> {
  private final CallableTestResultHandler<T> testResultHandler;

  private final T actual;

  public ThenAfterCalling(final CallableTestResultHandler<T> testResultHandler, final T actual) {
    this.testResultHandler = testResultHandler;
    this.actual = actual;
  }

  @Override
  public ThenAfterCalling<T> then(final Consumer<T> check) {
    try {
      check.accept(actual);
      return this;
    }
    catch (final Throwable e) {
      throw new TestInfoAddedAssertionError(testResultHandler.getTestInfo(), e);
    }
  }
}
