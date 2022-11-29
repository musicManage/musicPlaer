module.exports = {
  css: {
    loaderOptions: {
      sass: {
        // 全局引入变量和 mixin
        additionalData:
            '@import "@/assets/css/var.scss";'
      }
    }
  },
  transpileDependencies: true
}
