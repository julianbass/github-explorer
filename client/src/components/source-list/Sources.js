import React from "react";
import { SourceList, SourceListContainer } from "./Styles";

export default function Sources({ sources }) {
  return (
    <SourceListContainer>
      <SourceList>
        {sources.map((s) => {
          return (
            <li key={s.id}>
              <p>{s.owner}</p>
              <p>{s.repoName}</p>
            </li>
          );
        })}
      </SourceList>
    </SourceListContainer>
  );
}
