'use strict';
module.exports = function (grunt) {
    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Automatically load required grunt tasks
    require('jit-grunt')(grunt);

    grunt.initConfig({
        compass: {
            dist: {
                options: {
                    basePath: 'static',
                    sassDir: 'sass',
                    environment: 'production'
                }
            },
            dev: {
                options: {
                    watch: true,
                    basePath: 'static',
                    sassDir: 'sass'
                }
            }
        },
        watch: {
            css: {
                files: ['**/*.scss'],
                tasks: ['compass:dev'],
                options: {
                  spawn: false,
                }
            }
        }
    });
};
