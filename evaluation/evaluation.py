# ANALYSIS MODULE
# Running this file will grab the JSON file
# containing the analysis results and create the
# plots and tables in the src/evaluation/resources/out folder.

# built-in imports
import json
import os

# make sure pandas and matplotlib are installed
try:
    import pandas as pd
    from matplotlib import pyplot as plt
    from matplotlib.ticker import MultipleLocator
    from matplotlib import cycler
except ImportError:
    print("ImportError: Missing dependencies. Please install the following python packages:")
    print("pandas, matplotlib")
    exit(1)


# file and path constants
OUT_FOLDER = 'evaluation/out/'
FILE = 'results.json'
METRICS = ['exploredNodeCount', 'pathCost']

def load(path):
    with open(path, 'r') as f:
        data = json.load(f)
    return data

def filter_df(df, column, values):
    return df[df[column].isin(values)]

def set_plt_params():

    major = 5.0
    minor = 3.0

    plt.style.use('default')
    plt.rcParams['font.size'] = 15
    plt.rcParams['legend.fontsize'] = 18
    plt.rcParams['text.usetex'] = True
    plt.rcParams['xtick.minor.size'] = minor
    plt.rcParams['xtick.major.size'] = major
    plt.rcParams['ytick.minor.size'] = minor
    plt.rcParams['ytick.major.size'] = major
    plt.rcParams['xtick.direction'] = 'in'
    plt.rcParams['ytick.direction'] = 'in'
    plt.rcParams['axes.prop_cycle'] = cycler(color=["r", "b", "g", "m", "c", "y", "k"])

def create_plot(df: pd.DataFrame, file_name: str, title: str, ycol: str):

    xsize = 15
    ysize = 5

    fig, ax = plt.subplots(figsize=(xsize, ysize))

    ax = df.plot.bar(rot=0, figsize=(xsize, ysize))

    plt.legend(loc='best')
    plt.title(title)
    plt.ylabel(f'${ycol}$', labelpad = 10)
    plt.xticks(rotation = 90)
    plt.savefig(f"{OUT_FOLDER}{file_name}.png", dpi=300, pad_inches=.15, bbox_inches = 'tight')

if __name__ == '__main__':
    set_plt_params()
    data = load(OUT_FOLDER + FILE)
    df = pd.DataFrame(data)

    # Node Count
    df_node_count = df.pivot(index='conf', columns='algorithm', values='exploredNodeCount')
    df_node_count = df_node_count.reindex(["CONF" + str(i) for i in range(25)])
    create_plot(df_node_count, 'explored_nodes', 'Number of explored nodes', 'Configuration')

    # Path
    df_cost = df.pivot(index='conf', columns='algorithm', values='pathCost')
    df_cost = df_cost.reindex(["CONF" + str(i) for i in range(25)])
    create_plot(df_cost, 'path_cost', 'Path cost', 'Configuration')