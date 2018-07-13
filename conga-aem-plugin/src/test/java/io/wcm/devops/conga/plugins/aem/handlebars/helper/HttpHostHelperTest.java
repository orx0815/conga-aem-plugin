/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2017 wcm.io
 * %%
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
 * #L%
 */
package io.wcm.devops.conga.plugins.aem.handlebars.helper;

import static io.wcm.devops.conga.plugins.aem.handlebars.helper.TestUtils.executeHelper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.wcm.devops.conga.generator.spi.handlebars.HelperPlugin;
import io.wcm.devops.conga.generator.util.PluginManagerImpl;

public class HttpHostHelperTest {

  private HelperPlugin<Object> helper;

  @SuppressWarnings("unchecked")
  @BeforeEach
  public void setUp() {
    helper = new PluginManagerImpl().get(HttpHostHelper.NAME, HelperPlugin.class);
  }

  @Test
  public void testNull() throws Exception {
    Object httpHost = executeHelper(helper, null, new MockOptions());
    assertNull(httpHost);
  }

  @Test
  public void testContextWithPort() throws Exception {
    Object httpHost = executeHelper(helper, "localhost:8080", new MockOptions());
    assertTrue(httpHost instanceof String);
    assertEquals("localhost:8080", httpHost);
  }

  @Test
  public void testContextWithoutPort() throws Exception {
    Object httpHost = executeHelper(helper, "localhost", new MockOptions());
    assertTrue(httpHost instanceof String);
    assertEquals("localhost", httpHost);
  }

  @Test
  public void testWithCustomPort() throws Exception {
    Object httpHost = executeHelper(helper, "localhost", new MockOptions().withHash(AbstractHostHelper.HASH_OPTION_PORT, 8081));
    assertTrue(httpHost instanceof String);
    assertEquals("localhost:8081", httpHost);
  }

  @Test
  public void testWithDefaultPort() throws Exception {
    Object httpHost = executeHelper(helper, "localhost", new MockOptions().withHash(AbstractHostHelper.HASH_OPTION_PORT, HttpHostHelper.DEFAULT_PORT));
    assertTrue(httpHost instanceof String);
    assertEquals("localhost", httpHost);
  }

}
