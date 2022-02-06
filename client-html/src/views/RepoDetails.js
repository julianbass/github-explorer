import { View } from "/src/views/View.js";

export const COMMIT = 0;
export const ISSUES = 1;
export const PULLS = 2;

export const AUTHOR_NAME = 0;
export const CREATED_ON_ASC = 1;
export const CREATED_ON_DESC = 2;

export class RepoDetails extends View {
  constructor(props) {
    super();
    this.onRequestToggle = props.onRequestToggle;
    this.onSearch = props.onSearch;
    this.onSort = props.onSort;
    this.activeDetails = -1;
  }

  getHealthIconBgColor(percentage) {
    let backgroundColor = "red";
    if (percentage > 70) {
      backgroundColor = "green";
    } else if (percentage > 30) {
      backgroundColor = "orange";
    }

    return backgroundColor;
  }

  render(repo) {
    document.querySelector(".repo-panel").classList.add("show");
    const el = document.querySelector("#repo-details");
    el.classList.add("show");

    const backgroundColor = this.getHealthIconBgColor(repo.healthPercentage);

    const html = `
        <h2 id="repo-name">${repo.owner}/${repo.name}</h3>
        <div class="health-icon-wrapper" style="background-color: ${backgroundColor};">
            <p class="health-label">Health: ${repo.healthPercentage}</p>            
        </div>
        <p>Github Page: <a href="https://github.com/${repo.owner}/${repo.name}">https://github.com/${repo.owner}/${repo.name}</a></p>
       
        <label for="info-type">Show: </label>
        <select name="info-type" id="info-type">
            <option disabled selected value> -- select an option -- </option>
            <option value="${COMMIT}">Commits</option>
            <option value="${ISSUES}"">Issues</option>
            <option value="${PULLS}">Pulls</option>
        </select>
        <div class="sort-box">
       
        </div>    
        <div class="search-box">
            <label for="sort-by">Sort By: </label>
            <select class="sort-by" name="sort-by" id="sort-by">
                <option disabled selected value> -- Sort By -- </option>
                <option value="${AUTHOR_NAME}">Author</option>
                <option value="${CREATED_ON_ASC}">Latest</option>
                <option value="${CREATED_ON_DESC}">Oldest</option>
            </select> 
            <input id="search-contents" type="text" placeholder="Search.." name="search">
            <button id="search-btn"><i class="fa fa-search"></i></button>
        </div>    
        `;

    el.innerHTML = html;

    this.bindEventListeners();
  }

  bindEventListeners() {
    document.querySelector("#sort-by").addEventListener("change", (e) => {
      const selected = parseInt(e.target.value);
      selected > -1 && this.onSort && this.onSort(this.activeDetails, selected);
    });

    document.querySelector("#info-type").addEventListener("change", (e) => {
      const selected = parseInt(e.target.value);
      this.activeDetails = selected;
      selected > -1 && this.onRequestToggle(parseInt(e.target.value));
    });

    document.querySelector("#search-btn").addEventListener("click", () => {
      const val = document.querySelector("#search-contents").value;
      this.onSearch && this.onSearch(this.activeDetails, val);
    });
  }

  update(repo) {
    this.render(repo);
  }
}
