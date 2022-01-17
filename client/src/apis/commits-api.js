import axios from "axios";

export async function getCommits(owner, repo) {
  const { data } = await axios.get(`/api/repos/${owner}/${repo}`);
  console.log(data);
  return data;
}