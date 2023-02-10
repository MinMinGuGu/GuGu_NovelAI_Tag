import {Component} from "react";
import Content from "../../../layout/Content";
import Crud from "../../../components/Crud";
import apis from "../../../config/setting";
import {Button, Form, Input, message, Space, Upload} from "antd";
import {DownloadOutlined, UploadOutlined} from '@ant-design/icons';

export default class System extends Component {

    exportDataButton = () => {
        const alink = document.createElement("a");
        alink.href = apis.configApi + '/export';
        alink.click();
    }

    importDataButton = () => {
        console.log('我被点击了');
    }

    addTopComponent = () => {
        const props = {
            name: 'file',
            accept: ".json",
            showUploadList: false,
            action: apis.configApi + "/import",
            onChange(info) {
                if (info.file.status !== 'uploading') {
                    console.log(info.file, info.fileList);
                }
                if (info.file.status === 'done') {
                    message.success(`${info.file.name} 导入成功`);
                } else if (info.file.status === 'error') {
                    message.error(`${info.file.name} 导入失败`);
                }
            },
        };
        return (
            <Space>
                <Button type="primary" icon={<DownloadOutlined/>} onClick={this.exportDataButton}>下载系统数据</Button>
                <Upload {...props}>
                    <Button icon={<UploadOutlined/>}>导入系统数据</Button>
                </Upload>
            </Space>
        );
    }

    generateFormContext = () => {
        return (
            <div>
                <Form.Item label="key" name="key" rules={[
                    {
                        required: true,
                        message: "Please input key!",
                    },
                ]}>
                    <Input/>
                </Form.Item>
                <Form.Item label="value" name="value" rules={[
                    {
                        required: true,
                        message: "Please input value!",
                    },
                ]}>
                    <Input/>
                </Form.Item>
                <Form.Item label="描述" name="description">
                    <Input/>
                </Form.Item>
            </div>
        )
    }

    content = () => {
        const columns = [
            {
                title: "id",
                dataIndex: "id",
                key: "id",
            },
            {
                title: "key",
                dataIndex: "key",
                key: "key",
            },
            {
                title: "value",
                dataIndex: "value",
                key: "value",
            },
            {
                title: "描述",
                dataIndex: "description",
                key: "description",
            },
        ];
        return (
            <div>
                <Crud url={apis.configApi} tabColumns={columns} export={false}
                      generateFormContext={this.generateFormContext.bind(this)}
                      addTopComponent={this.addTopComponent.bind(this)}
                      failJump
                />
            </div>
        );
    };

    render() {
        return <Content view={this.content()}/>;
    }
}
