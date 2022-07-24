import styled from "styled-components";
import "../../default.css";
import * as Default from "../../Default";
import { useEffect, useRef, useState } from "react";

export const questionTypeOptions = [
  "implementation",
  "arithmetic",
  "math",
  "geometry",
  "dp",
  "graphs",
  "topological_sorting",
  "bruteforcing",
  "combinatorics",
  "graph_traversal",
  "bfs",
  "dfs",
  "regex",
  "string",
  "bitmask",
  "flow",
  "dp_bitfield",
  "sorting",
  "number_theory",
  "primality_test",
  "sieve",
  "bipartite_matching",
  "data_structures",
  "deque",
  "greedy",
  "divide_and_conquer",
  "recursion",
  "euclidean",
  "ad_hoc",
  "arbitrary_precision",
  "backtracking",
  "parsing",
  "disjoint_set",
  "mitm",
  "mst",
  "line_intersection",
  "sweeping",
  "prefix_sum",
  "simulation",
  "binary_search",
  "floyd_warshall",
  "priority_queue",
  "convex_hull",
  "pythagoras",
  "fft",
  "trees",
  "case_work",
  "mfmc",
  "eulerian_path",
  "point_in_convex_polygon",
  "polygon_area",
  "ternary_search",
  "probability",
  "kmp",
  "knapsack",
  "hash_set",
  "scc",
  "parametric_search",
  "exponentiation_by_squaring",
  "dp_tree",
  "dp_connection_profile",
  "geometry_3d",
  "queue",
  "dijkstra",
  "hashing",
  "heuristics",
  "precomputation",
  "trie",
  "segtree",
  "cactus",
  "biconnected_component",
  "constructive",
  "a_star",
  "game_theory",
  "tree_set",
  "calculus",
  "numerical_analysis",
  "2_sat",
  "coordinate_compression",
  "lazyprop",
  "lis",
  "stack",
  "bellman_ford",
  "extended_euclidean",
  "two_pointer",
  "mcmf",
  "0_1_bfs",
  "duality",
  "hungarian",
  "linear_programming",
  "sliding_window",
  "rotating_calipers",
  "pigeonhole_principle",
  "crt",
  "discrete_log",
  "mobius_inversion",
  "lca",
  "pbs",
  "linked_list",
  "inclusion_and_exclusion",
  "modular_multiplicative_inverse",
  "gaussian_elimination",
  "linear_algebra",
  "sprague_grundy",
  "permutation_cycle_decomposition",
  "articulation",
  "rabin_karp",
  "suffix_array",
  "sparse_table",
  "multi_segtree",
  "point_in_non_convex_polygon",
  "planar_graph",
  "smaller_to_larger",
  "miller_rabin",
  "pollard_rho",
  "dancing_links",
  "knuth_x",
  "cht",
  "link_cut_tree",
  "offline_queries",
  "tsp",
  "berlekamp_massey",
  "kitamasa",
  "min_enclosing_circle",
  "tree_isomorphism",
  "dp_deque",
  "bidirectional_search",
  "randomization",
  "simulated_annealing",
  "merge_sort_tree",
  "aho_corasick",
  "euler_tour_technique",
  "splay_tree",
  "mo",
  "hld",
  "bitset",
  "pst",
  "euler_characteristic",
  "dual_graph",
  "physics",
  "manacher",
  "half_plane_intersection",
  "statistics",
  "slope_trick",
  "voronoi",
  "euler_phi",
  "stable_marriage",
  "burnside",
  "matroid",
  "circulation",
  "flt",
  "delaunay",
  "divide_and_conquer_optimization",
  "rope",
  "bipartite_graph",
  "centroid",
  "centroid_decomposition",
  "sqrt_decomposition",
  "pick",
  "hirschberg",
  "dominator_tree",
  "hall",
  "knuth",
  "linearity_of_expectation",
  "directed_mst",
  "green",
  "tree_decomposition",
  "palindrome_tree",
  "differential_cryptanalysis",
  "geometric_boolean_operations",
  "geometry_hyper",
  "majority_vote",
  "chordal_graph",
  "utf8",
  "lucas",
  "offline_dynamic_connectivity",
  "monotone_queue_optimization",
  "stoer_wagner",
  "hackenbush",
  "general_matching",
  "generating_function",
  "z",
  "bayes",
  "suffix_tree",
  "alien",
  "tree_compression",
  "rb_tree",
  "discrete_sqrt",
  "top_tree",
];
const difficultyGradeOptions = [
  "Bronze",
  "Silver",
  "Gold",
  "Platinum",
  "Diamond",
  "Ruby",
];
const difficultyLevelOptions = [1, 2, 3, 4, 5];

export const FilterElement = ({ typo, id, className }) => {
  const [filterClicked, onClickFilter] = useState(false);
  const clickFilter = () => {
    onClickFilter((prev) => !prev);
  };

  const divRef = useRef();

  useEffect(() => {
    if (divRef) {
      onClickFilter(divRef.current.checked);
    }
  }, [divRef?.current?.checked]);

  return (
    <div
      checked={filterClicked}
      ref={divRef}
      id={id}
      value={typo}
      className={className}
      style={{
        minWidth: "32px",
        height: "32px",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: filterClicked ? "#0083e8" : "#b8b8b8",
        border: filterClicked ? "solid 1px #0083e8" : null,
        borderRadius: "3px",
        color: "#fff",
        fontSize: "14px",
        boxSizing: "border-box",
        margin: "4px",
        padding: "0 4px",
        cursor: "pointer",
        transition: "all 0.1s ease-out",
      }}
      onClick={clickFilter}
    >
      <div>{typo}</div>
      <div
        style={{
          fontSize: "12.8px",
          margin: "0 0 0 8px",
        }}
        className={filterClicked ? "" : "hidden"}
      >
        x
      </div>
    </div>
  );
};

export const DifficultyFilter = ({ page }) => {
  // const [checked, setChecked] = useState < Boolean > ([]);

  return (
    <div>
      <div style={{ display: "flex", margin: "5px 0" }}>
        <div style={{ width: "130px" }}>Unranked</div>
        <FilterElement typo="Unranked" id={`${page}-0`} />
      </div>
      {difficultyGradeOptions.map((grade, i) => {
        return (
          <div style={{ display: "flex", margin: "5px 0" }}>
            <div style={{ width: "130px" }}>{grade}</div>
            {difficultyLevelOptions.map((op, j) => (
              <FilterElement typo={op} id={`${page}-${5 * i + 5 - j}`} />
            ))}
          </div>
        );
      })}
    </div>
  );
};

export const DailyFilter = ({ id }) => {
  return (
    <div id={id}>
      <div
        style={{
          display: "flex",
          flexWrap: "wrap",
          boxSizing: "border-box",
          margin: "5px 0",
        }}
      >
        {questionTypeOptions.map((op) => (
          <FilterElement
            typo={op}
            id={id + "-element-" + op}
            className={id + "-element"}
          />
        ))}
      </div>
    </div>
  );
};

const DaysFilter = ({ id }) => {
  const days = [
    ["mon", "월"],
    ["tue", "화"],
    ["wed", "수"],
    ["thu", "목"],
    ["fri", "금"],
    ["sat", "토"],
    ["sun", "일"],
  ];

  const DayFilterElement = ({ typo }) => {
    const [onMouseOver, setMouseOver] = useState(false);
    const getMouseOver = () => {
      setMouseOver(true);
    };
    const getMouseOut = () => {
      setMouseOver(false);
    };

    const [dayFilter, dayFilterOnClick] = useState(false);
    const clickDayFilter = () => {
      dayFilterOnClick((prev) => !prev);
      const dropdown = document.querySelector(`.dropdown_${typo[1]}`);
      if (dayFilter) dropdown.classList.add("hidden");
      else dropdown.classList.remove("hidden");
    };
    // dayFilter 값이 바뀐 다음에 dropdown을 가져오도록 async, await 사용

    return (
      <Default.DaysFilter
        checked={dayFilter}
        style={{
          background: dayFilter || onMouseOver ? "#0083e8" : "#fff",
          color: dayFilter || onMouseOver ? "#fff" : "#0083e8",
        }}
        onClick={clickDayFilter}
        onMouseOver={getMouseOver}
        onMouseOut={getMouseOut}
        id={id + "-" + typo[0]}
      >
        {typo[1]}
      </Default.DaysFilter>
    );
  };

  const DropDown = ({ typo }) => {
    return (
      <div
        className={`dropdown_${typo[1]} hidden`}
        id={id + "-dropdown-" + typo[0]}
      >
        <Default.SelectBoxContainer>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              boxSizing: "border-box",
              marginRight: "10px",
              color: "#333",
              fontSize: "16px",
              fontWeight: 600,
            }}
          >
            {typo[1]}
          </div>
          {questionTypeOptions.map((op) => (
            <FilterElement typo={op} id={id + "-element-" + typo[0]} />
          ))}
        </Default.SelectBoxContainer>
      </div>
    );
  };

  return (
    <div style={{ margin: "15px 0" }}>
      <Default.DaysFilterContainer>
        {days.map((day) => (
          <DayFilterElement typo={day} />
        ))}
      </Default.DaysFilterContainer>
      {days.map((day) => (
        <DropDown typo={day} />
      ))}
    </div>
  );
};

export const ScheduleFilter = ({ id }) => {
  return <DaysFilter id={id} />;
};

export const InfoContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  padding: 20px 0;
`;

export const InfoContainerLabel = styled.div`
  width: 100%;
  border-bottom: solid 2px #333;
  box-sizing: border-box;
  padding: 10px 0;
  margin-bottom: 20px;
  color: #333;
  font-size: 20px;
  font-weight: 600;
`;

export const InfoLabel = styled.span`
  min-width: 50px;
  margin: 0 10px;
  font-size: 20px;
`;

export const InfoSubContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: "space-between";
  align-items: center;
`;

export const Input = styled.input`
  width: 100%;
  max-width: 530px;
  height: 40px;
  box-sizing: border-box;
  border: solid 1px #bbb;
  padding: 0 24px;
  margin: 5px 0;
`;

export const ShortInput = styled.input`
  width: 470px;
  height: 40px;
  box-sizing: border-box;
  border: solid 1px #bbb;
  padding: 0 24px;
  margin: 5px 0;
`;
