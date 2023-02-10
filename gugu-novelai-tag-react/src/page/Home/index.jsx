import {Component} from "react";
import {Divider, Input, message, Skeleton, Tabs, Tag} from "antd";
import Content from "../../layout/Content";
import AttrTableTransfer from "../../components/AttrTableTransfer";
import {get} from "../../utils/requestUtil";
import {copyToClipboard} from "../../utils/copyUtil";
import {getUniqueRandomColor} from "../../utils/colorUtil";
import apis from "../../config/setting";

export default class Home extends Component {
    state = {
        source: null,
        attrKeyMap: new Map(),
        selectedAttrMap: new Map(),
        tabsItems: [],
        inputSearchValue: '',
        loading: true,
    };

    UNSAFE_componentWillMount = () => {
        this.loadData();
    };

    loadData = () => {
        // 后端 > 本地 > null
        get(apis.indexApi + '/data').then(json => {
            let data = json.data;
            this.setState({source: data});
            this.generateTabs(data);
            this.setState({loading: false});
        }).catch(error => {
            console.error('请求后端加载数据失败');
            message.warning("无法从后端获取数据，将加载本地资源且无法使用管理功能。");
            fetch('/data.json').then(response => {
                if (!response.ok) {
                    message.error("无法加载本地资源");
                    return;
                }
                return response.text();
            }).then(text => {
                let data = JSON.parse(text);
                this.setState({source: data});
                this.generateTabs(data);
                this.setState({loading: false});
                message.success("加载本地资源成功");
            }).catch(error => {
                console.error('Request failed: ', error);
                message.error("无法加载本地资源");
            });
        });
    }

    generateTabs = (data) => {
        if (Object.getOwnPropertyNames(data).length === 0) {
            return;
        }
        if (data.length < 1) {
            message.error("解析到空数据");
            return;
        }
        let index = 0;
        let tabs = [];
        for (const datum of data) {
            if (index++ === 0) {
                tabs.push({
                    label: "全部元素",
                    key: index,
                    children: <AttrTableTransfer handleAddTargetKeys={this.handleAddTargetKeys.bind(this)}
                                                 handleDelTargetKeys={this.handleDelTargetKeys.bind(this)}
                                                 data={this.getData(data, null, true)}/>
                });
                index += 1;
            }
            tabs.push({
                label: datum.category,
                key: index,
                children: <AttrTableTransfer handleAddTargetKeys={this.handleAddTargetKeys.bind(this)}
                                             handleDelTargetKeys={this.handleDelTargetKeys.bind(this)}
                                             data={this.getData(datum.attrList, datum.category)}/>
            });
        }
        this.setState({tabsItems: tabs});
    }

    dataIdCount = 0;
    addAttrIdAndKeyProperty = (attr, category) => {
        if (!attr.id) {
            attr.id = this.dataIdCount.toString();
            attr.categoryName = category;
            this.dataIdCount += 1;
        }
        if (!attr.key) {
            attr.key = attr.id.toString();
        }
        return attr;
    }

    getData = (data, category, allFlag) => {
        if (!data) {
            return null;
        }
        let dataList = [];
        if (allFlag) {
            for (const datum of data) {
                let attrList = datum.attrList.map(item => this.addAttrIdAndKeyProperty(item, datum.category));
                dataList = [...dataList, ...attrList,];
            }
            const {attrKeyMap} = this.state;
            for (const dataListElement of dataList) {
                attrKeyMap.set(dataListElement.key, dataListElement);
            }
            return dataList;
        }
        let attrList = data.map(item => this.addAttrIdAndKeyProperty(item, category));
        dataList = [...attrList];
        return dataList;
    }

    toRefreshInputSearch = () => {
        const {selectedAttrMap} = this.state;
        let itemValue = '';
        selectedAttrMap.forEach((item, key) => {
            let weight = item.weight;
            if (weight > 0) {
                itemValue += '{'.repeat(weight) + item.value + '}'.repeat(weight) + ',';
            }
            if (weight < 0) {
                itemValue += '['.repeat(-weight) + item.value + ']'.repeat(-weight) + ',';
            }
            if (weight === 0) {
                itemValue += item.value + ',';
            }
        });
        this.setState({inputSearchValue: itemValue});
    }

    handleAddTargetKeys = (targetKeys) => {
        const {attrKeyMap, selectedAttrMap} = this.state;
        let targetAttrList = [];
        for (const targetKey of targetKeys) {
            let attr = attrKeyMap.get(targetKey.key);
            attr.weight = targetKey.weight;
            targetAttrList.push(attr);
        }
        for (const targetAttr of targetAttrList) {
            selectedAttrMap.set(targetAttr.key, targetAttr);
        }
        this.toRefreshInputSearch();
    }

    handleDelTargetKeys = (targetKeys) => {
        if (targetKeys.length < 1) {
            return;
        }
        const {selectedAttrMap} = this.state;
        for (const targetKey of targetKeys) {
            selectedAttrMap.delete(targetKey);
        }
        this.toRefreshInputSearch();
    }

    inputSearchOnCopy = (value) => {
        let clipboard = copyToClipboard(value);
        if (clipboard instanceof Promise) {
            clipboard.then(() => {
                console.log('Text copied to clipboard successfully!');
                message.success("复制成功");
            }).catch((error) => {
                console.error('Failed to copy text: ', error);
                message.error("复制失败");
            })
            return;
        }
        if (clipboard) {
            message.success("复制成功");
            return;
        }
        message.error("复制失败");
    }

    generateTags = () => {
        const {selectedAttrMap} = this.state;
        let categoryNameList = new Set();
        selectedAttrMap.forEach((item, key) => {
            categoryNameList.add(item.categoryName);
        });
        let tags = [];
        for (const categoryNameListElement of categoryNameList) {
            tags.push(<Tag color={getUniqueRandomColor()}>{categoryNameListElement}</Tag>)
        }
        return tags;
    }

    content = () => {
        const {loading, tabsItems, inputSearchValue} = this.state;
        return (
            <Skeleton loading={loading}>
                <Tabs
                    defaultActiveKey="0"
                    items={tabsItems}
                />
                <Divider orientation="content">咒语</Divider>
                {this.generateTags()}
                <Divider dashed={true}/>
                <Input.Search
                    readOnly={true}
                    disabled={inputSearchValue.length < 1}
                    value={inputSearchValue}
                    enterButton="复制"
                    size="large"
                    onSearch={this.inputSearchOnCopy}
                />
            </Skeleton>
        );
    };

    render() {
        return <Content view={this.content()}/>;
    }
}
