import { useEffect, useState } from "react";
import "./App.css";
import { getRepos } from "./apis/repo-api";
import { Header } from "./components/header/Styles";
import Sources from "./components/source-list/Sources";

function App() {
  const [sources, setSources] = useState([]);
  useEffect(() => {
    async function getData() {
      const data = await getRepos();
      console.log(data);
      setSources(data);
    }

    getData();
  }, []);

  return (
    <div className="App" style={{ position: "relative" }}>
      <Header>
        <p>Git Explorer</p>
      </Header>
      <Sources sources={sources} />
    </div>
  );
}

export default App;
