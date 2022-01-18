export class RepoList {
    constructor(props) {
        this.onRepoSelect = props.onRepoSelect;
        this.activeRepo = -1;
    }

    render(repos) {
            const el = document.querySelector(".repo-list");

            const html = `${repos
      .map((repo, index) => {
        console.log(index, this.activeRepo);
        const isActive = index === this.activeRepo;
       
        return `
                <li id="repo-${index}" class="${isActive ? "active-repo" : ""}">
                    <p>${repo.owner}/${repo.repoName}</p>
                </li>
            `;
      })
      .join("\n")}`;

    console.log(html);
    el.innerHTML = html;

    this.bindEventListeners(repos);
  }

  bindEventListeners(repos) {
    repos.forEach((repo, index) => {
      document.querySelector(`#repo-${index}`).addEventListener("click", () => {
        this.onRepoSelect && this.onRepoSelect(repo.owner, repo.repoName);
        this.activeRepo = index;
        this.render(repos);
      });
    });
  }

  update(repos) {
    this.render(repos);
  }
}