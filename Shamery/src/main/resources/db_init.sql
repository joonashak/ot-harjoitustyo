CREATE TABLE IF NOT EXISTS ScoreType (
  name TEXT UNIQUE
);

INSERT OR IGNORE INTO ScoreType (name) VALUES ("Points Game"), ("Time Trial");

CREATE TABLE IF NOT EXISTS Player (
  name TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS Score (
  player_id INT,
  score_type_id INT,
  score INT,
  FOREIGN KEY (player_id) REFERENCES Player (rowid),
  FOREIGN KEY (score_type_id) REFERENCES ScoreType (rowid)
);
