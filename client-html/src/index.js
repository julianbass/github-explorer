import { CommitsAPI } from "/src/apis/CommitsAPI.js";
import { ReposAPI } from "/src/apis/ReposAPI.js";
import { MetricsAPI } from "/src/apis/MetricsAPI.js";
import { Commit } from "/src/components/Commit.js";

export function onGetCommitsSelect(owner, repo) {
  return async () => {
    const api = new CommitsAPI(owner, repo);
    const commits = await api.getCommits();
    new Commit(commits).render();
  };
}

export async function loadRepo(owner, name) {
  document.querySelector(".repo-panel").classList.add("show");
  document.querySelector(".commits-panel").innerHTML = "";
  const header = document.querySelector("#repo-name");
  header.innerHTML = `${owner}/${name}`;
  const healthStatus = document.querySelector("#health-status");
  const api = new MetricsAPI(owner, name);
  const metrics = await api.getMetrics();

  const healthPerc = metrics.healthPercentage;
  let backgroundColor = "red";
  if (healthPerc > 70) {
    backgroundColor = "green";
  } else if (healthPerc > 30) {
    backgroundColor = "orange";
  }

  healthStatus.innerHTML = `${healthPerc}`;
  healthStatus.style.backgroundColor = backgroundColor;
  
  const btn = document.querySelector("#commit-btn");
  if(btn) btn.remove();
  
  const commitBtn = document.createElement("button");
  commitBtn.innerHTML = "Commits";
  commitBtn.setAttribute("id", "commit-btn") 
  commitBtn.addEventListener("click", onGetCommitsSelect(owner, name));
  const repoPanel = document.querySelector(".repo-panel");
  repoPanel.classList.add("show");
  repoPanel.appendChild(commitBtn);
}

export async function onGetReposSelect() {
  const api = new ReposAPI();
  const repos = await api.getRepos();

  const el = document.querySelector(".repo-list");

  if (repos && repos.length) {
    repos.forEach((repo) => {
      const repoLi = document.createElement("li");
      repoLi.addEventListener("click", () =>
        loadRepo(repo.owner, repo.repoName)
      );
      const repoName = document.createElement("p");
      repoName.innerHTML = `${repo.owner}/${repo.repoName}`;
      repoLi.appendChild(repoName);
      el.appendChild(repoLi);
    });
  }
}

function showNewRepoInput() {
  document.querySelector(".add-repo-panel").classList.add("show");
  document.querySelector("#add-repo-submit").addEventListener("click", () => {
    const owner = document.querySelector("#owner").value;
    const repoName = document.querySelector("#repo-name").value;
    console.log(owner, repoName);
    document.querySelector(".add-repo-panel").classList.remove("show");
  });
}

export function addListeners() {
  document
    .querySelector("#add-repo-btn")
    .addEventListener("click", () => showNewRepoInput());
}

function load() {
  //   onGetCommitsSelect();
  onGetReposSelect();
  addListeners();
}

window.onload = load;
