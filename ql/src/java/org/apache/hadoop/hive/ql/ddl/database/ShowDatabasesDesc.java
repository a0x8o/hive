/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql.ddl.database;

import java.io.Serializable;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.ddl.DDLDesc;
import org.apache.hadoop.hive.ql.ddl.DDLTask2;
import org.apache.hadoop.hive.ql.plan.Explain;
import org.apache.hadoop.hive.ql.plan.Explain.Level;

/**
 * DDL task description for SHOW DATABASES commands.
 */
@Explain(displayName = "Show Databases", explainLevels = { Level.USER, Level.DEFAULT, Level.EXTENDED })
public class ShowDatabasesDesc implements DDLDesc, Serializable {
  private static final long serialVersionUID = 1L;

  public static final String SHOW_DATABASES_SCHEMA = "database_name#string";

  static {
    DDLTask2.registerOperation(ShowDatabasesDesc.class, ShowDatabasesOperation.class);
  }

  private final String resFile;
  private final String pattern;

  public ShowDatabasesDesc(Path resFile) {
    this.resFile = resFile.toString();
    this.pattern = null;
  }

  public ShowDatabasesDesc(Path resFile, String pattern) {
    this.resFile = resFile.toString();
    this.pattern = pattern;
  }

  @Explain(displayName = "pattern")
  public String getPattern() {
    return pattern;
  }

  @Explain(displayName = "result file", explainLevels = { Level.EXTENDED })
  public String getResFile() {
    return resFile;
  }
}
