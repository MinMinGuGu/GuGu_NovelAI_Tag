import {Component} from "react";
import difference from 'lodash/difference';
import {Button, Col, Divider, InputNumber, message, Row, Space, Table, Transfer} from "antd";

const TableTransfer = ({leftColumns, rightColumns, ...restProps}) => (
    <Transfer {...restProps}>
        {({
              direction,
              filteredItems,
              onItemSelectAll,
              onItemSelect,
              selectedKeys: listSelectedKeys,
              disabled: listDisabled,
          }) => {
            const columns = direction === 'left' ? leftColumns : rightColumns;
            const rowSelection = {
                getCheckboxProps: (item) => ({
                    disabled: listDisabled || item.disabled,
                }),
                onSelectAll(selected, selectedRows) {
                    const treeSelectedKeys = selectedRows
                        .filter((item) => !item.disabled)
                        .map(({key}) => key);
                    const diffKeys = selected
                        ? difference(treeSelectedKeys, listSelectedKeys)
                        : difference(listSelectedKeys, treeSelectedKeys);
                    onItemSelectAll(diffKeys, selected);
                },
                onSelect({key}, selected) {
                    onItemSelect(key, selected);
                },
                selectedRowKeys: listSelectedKeys,
            };
            return (
                <Table
                    rowSelection={rowSelection}
                    columns={columns}
                    dataSource={filteredItems}
                    size="small"
                    style={{
                        pointerEvents: listDisabled ? 'none' : undefined,
                    }}
                    onRow={({key, disabled: itemDisabled}) => ({
                        onClick: (event) => {
                            if (itemDisabled || listDisabled) return;
                            onItemSelect(key, !listSelectedKeys.includes(key));
                        },
                    })}
                />
            );
        }}
    </Transfer>
);

export default class Index extends Component {

    state = {
        targetKeys: [],
        targetKeyWeightMap: new Map(),
    }

    mockData = () => {
        return Array.from({
            length: 20,
        }).map((_, i) => ({
            key: i.toString(),
            id: `id_${i + 1}`,
            name: `name_${i + 1}`,
            value: `value${i + 1}`,
        }));
    }

    tableTransferOnChange = (nextTargetKeys) => {
        const {targetKeyWeightMap, targetKeys} = this.state;
        let ghostKeyList = [...targetKeys];
        for (const nextTargetKey of nextTargetKeys) {
            ghostKeyList = ghostKeyList.filter(item => item !== nextTargetKey);
        }
        for (const ghostKeyListElement of ghostKeyList) {
            targetKeyWeightMap.delete(ghostKeyListElement);
        }
        this.props.handleDelTargetKeys(ghostKeyList);
        this.setState({targetKeys: nextTargetKeys})
    }

    addBut = () => {
        const {targetKeys, targetKeyWeightMap} = this.state;
        const newTargetKeys = [];
        for (const targetKey of targetKeys) {
            let weight = targetKeyWeightMap.get(targetKey);
            newTargetKeys.push({key: targetKey, weight: weight ?? 0})
        }
        this.props.handleAddTargetKeys(newTargetKeys);
    }

    delBut = () => {
        const {targetKeys} = this.state;
        this.setState({targetKeys: []});
        this.props.handleDelTargetKeys(targetKeys);
    }

    InputNumberChange = (value, record) => {
        const {targetKeyWeightMap} = this.state;
        targetKeyWeightMap.set(record.key, value);
    }

    render() {
        const {targetKeys} = this.state;
        const leftTableColumns = [
            {
                dataIndex: 'id',
                title: 'id',
            },
            {
                dataIndex: 'categoryName',
                title: '所属分类',
            },
            {
                dataIndex: 'name',
                title: '元素名',
            },
            {
                dataIndex: 'value',
                title: '元素值',
            },
        ];
        const rightTableColumns = [
            {
                dataIndex: 'name',
                title: '元素名',
            },
            {
                dataIndex: 'value',
                title: '元素值',
            },
            {
                dataIndex: 'id',
                title: '权重',
                render: (text, record, index) => {
                    let values = record.value;
                    let pattern = /^(\w+\s?\w+)+$/i.exec(values);
                    let disabled = pattern ? pattern[0] !== values : true;
                    if (disabled) {
                        message.warning("当值为复合值时不支持操作权重");
                    }
                    return <div onClick={e => e.stopPropagation()}><InputNumber disabled={disabled} defaultValue={0}
                                                                                onChange={e => this.InputNumberChange(e, record)}/>
                    </div>;
                },
            },
        ];
        return (
            <div>
                <Row gutter={16}>
                    <Col span={24}>
                        <TableTransfer
                            dataSource={this.props.data ?? this.mockData()}
                            targetKeys={targetKeys}
                            showSearch={true}
                            onChange={this.tableTransferOnChange}
                            leftColumns={leftTableColumns}
                            rightColumns={rightTableColumns}
                            filterOption={(inputValue, item) => item.id.indexOf(inputValue) !== -1 || item.categoryName.indexOf(inputValue) !== -1 || item.name.indexOf(inputValue) !== -1 || item.value.indexOf(inputValue) !== -1}
                        />
                    </Col>
                </Row>
                <Divider/>
                <Row gutter={16} type="flex" justify="center" align="middle">
                    <Col>
                        <Space>
                            <Button disabled={targetKeys.length < 1} type="primary" onClick={this.addBut}>生成咒语</Button>
                            <Button disabled={targetKeys.length < 1} type="primary" danger
                                    onClick={this.delBut}>删除咒语</Button>
                        </Space>
                    </Col>
                </Row>
            </div>
        )
    }
}