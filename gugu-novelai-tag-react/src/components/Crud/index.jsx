import {Component} from "react";
import {del, get, post} from "../../utils/requestUtil";
import {Button, Divider, Form, message, Modal, Popconfirm, Space, Table} from "antd";
import Search from "antd/es/input/Search";
import {withRouter} from "react-router-dom";

class Crud extends Component {

    addModalButton = null;

    UNSAFE_componentWillMount = () => {
        this.initData();
    };

    UNSAFE_componentWillReceiveProps = (nextProps, nextContext) => {
        this.initData();
    }

    initData = () => {
        this.setState({loading: true});
        get(this.props.url).then((result) => {
            let data = result.data;
            data = this.generateKeyFromData(data);
            this.setState({
                dataSource: data,
                total: data.total,
                pageSize: data.size,
                current: data.current,
                loading: false
            });
        }).catch(error => {
            console.error('无法加载数据', error);
            message.error("无法加载数据");
            if (this.props.failJump) {
                this.props.history.push('/');
            }
        });
    };

    state = {
        loading: this.props.loading,
        modalVisible: false,
        addConfirmLoading: false,
        dataSource: [],
        selectedRowKeys: [],
    };

    generateKeyFromData = (data) => {
        return data.map(item => {
            if (!item.key) {
                item.key = item.id.toString()
            }
            return item;
        });
    }

    postDeleteConfirm = (record) => {
        this.setState({loading: true});
        del(this.props.url + "/" + record.id).then((result) => {
            message.success(`删除成功`);
            this.setState({loading: false});
            this.initData();
        }).catch(error => {
            this.setState({loading: false});
            message.error(`删除失败`);
            console.error("删除失败", error);
        });
    };


    postFormData = (values) => {
        this.setState({addConfirmLoading: true});
        post(this.props.url, values).then((json) => {
            if (json.code === 200) {
                message.success("新增数据成功");
                this.setState({
                    addConfirmLoading: false,
                    modalVisible: false
                });
                this.initData();
            } else {
                message.error("新增数据失败");
                this.setState({
                    addConfirmLoading: false,
                });
            }
        }).catch(error => {
            this.setState({addConfirmLoading: false});
            message.error(`提交数据失败`);
            console.error("提交数据失败", error);
        });
    };

    onSearch = (text) => {
        this.setState({loading: true});
        get(this.props.url + `?search=${text}`).then(json => {
            let data = json.data;
            data = this.generateKeyFromData(data);
            this.setState({loading: false, dataSource: data});
        }).catch(error => {
            console.error('请求后端搜索数据失败', error);
            message.error("请求后端搜索数据失败");
        })
    }

    generateDeleteButton = (text, record, index) => {
        return (
            <div onClick={e => {
                e.stopPropagation()
            }}>
                <Popconfirm
                    title={`是否删除这条数据?`}
                    onConfirm={() => this.postDeleteConfirm(record)}
                >
                    <Button type="primary" disabled={record.systemDefault} danger>
                        删除
                    </Button>
                </Popconfirm>
            </div>
        );
    };

    batchDelete = () => {
        const {selectedRowKeys} = this.state;
        this.setState({loading: true});
        del(this.props.url + '/delete', {
            headers: {
                'content-type': 'application/json',
            },
            body: JSON.stringify(selectedRowKeys.map(item => Number(item))),
        }).then(json => {
            message.success("删除成功");
            this.setState({loading: false, selectedRowKeys: []});
            this.initData();
        }).catch(error => {
            console.error('请求后端搜索数据失败', error);
            message.error("请求后端搜索数据失败");
        })
    }

    exportDataSource = () => {
        const alink = document.createElement("a");
        alink.href = this.props.url + '/export';
        alink.click();
    }

    generateModalContext = () => {
        return (
            <div>
                <Form
                    labelCol={{
                        span: 6,
                    }}
                    wrapperCol={{
                        span: 16,
                    }}
                    initialValues={{
                        remember: true,
                    }}
                    onFinish={this.postFormData}
                >
                    {this.props.generateFormContext()}
                    <Form.Item style={{display: "none"}}>
                        <Button
                            ref={(obj) => (this.addModalButton = obj)}
                            type="primary"
                            htmlType="submit"
                        >
                            Submit
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        );
    };

    render() {
        const {
            loading,
            dataSource,
            modalVisible,
            addConfirmLoading,
            selectedRowKeys
        } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: (newSelectedRowKeys) => {
                console.log('selectedRowKeys changed: ', newSelectedRowKeys);
                this.setState({selectedRowKeys: newSelectedRowKeys})
            },
        };
        let columns = [...this.props.tabColumns, {
            title: "操作",
            key: "action",
            render: (text, record, index) => {
                return this.generateDeleteButton(text, record, index);
            },
        },];
        return (
            <div>
                <Space>
                    <Search placeholder="input search text" onSearch={this.onSearch} enterButton allowClear
                            loading={loading} disabled={loading}/>
                    <Button type="primary" loading={loading}
                            onClick={() => this.setState({modalVisible: true})}>新增</Button>
                    {this.props.addTopComponent ? this.props.addTopComponent() : null}
                </Space>
                <Divider dashed={true}/>
                <Space>
                    <Button type="primary" danger loading={loading} disabled={selectedRowKeys.length < 1}
                            onClick={this.batchDelete}>删除选中</Button>
                    <span style={(this.props.export ?? true) ? {} : {display: 'none'}}>
                    <Button type="primary" disabled={!(this.props.export ?? true)} loading={loading}
                            onClick={this.exportDataSource}>导出数据</Button>
                    </span>
                </Space>
                <Table
                    style={{marginTop: '10px'}}
                    rowSelection={rowSelection}
                    onRow={({key}) => ({
                        onClick: (event) => {
                            let selectedList = [...selectedRowKeys]
                            if (selectedRowKeys.indexOf(key) !== -1) {
                                selectedList = selectedList.filter(item => item !== key);
                            } else {
                                selectedList.push(key);
                            }
                            this.setState({selectedRowKeys: selectedList})
                        },
                    })}
                    columns={columns}
                    dataSource={dataSource}
                    loading={loading}
                />
                <Modal
                    title="新增"
                    open={modalVisible}
                    onOk={() => this.addModalButton.click()}
                    confirmLoading={addConfirmLoading}
                    onCancel={() => this.setState({modalVisible: false})}
                    maskClosable={false}
                    okText="确定"
                    cancelText="取消"
                    destroyOnClose={true}
                    keyboard={true}
                >
                    {this.generateModalContext()}
                </Modal>
            </div>
        );
    }
}

export default withRouter(Crud);